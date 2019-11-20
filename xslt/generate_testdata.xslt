<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE xml>
<xsl:stylesheet version="2.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zenta="http://magwas.rulez.org/zenta"
	xmlns:zentatools="java:org.rulez.magwas.zentatools.XPathFunctions"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" version="1.0" encoding="utf-8"
		indent="yes" omit-xml-declaration="yes" />

	<xsl:include href="xslt/functions.xslt" />

	<xsl:param name="outputbase" />

	<xsl:function name="zenta:extractTestArtifact">
		<xsl:param name="element"/>
		<xsl:param name="level"/>
	        <artifact>
		        <xsl:copy-of select="$element/@name"/>
		        <xsl:attribute name="TestData" select="zenta:neighbours($doc,$element,'contains,2')/@name"/>
		        <xsl:variable name="type" select="zenta:neighbours($doc,$element,'is a/is the type of,1')"/>
		        <xsl:attribute name="type" select="$type/@name"/>
		        <xsl:copy-of select="$type/@xsi-type"/>
		        <xsl:if test="($type/@xsi:type='DTO' or $type/@xsi:type = 'Entity') and $level=0">
		        <xsl:for-each select="zenta:neighbours($doc,$element,'is based on/is base of,1')">
		        	<member>
			        	<xsl:variable name="fieldsOfArtifact" select="zenta:neighbours($doc,.,'has an example as/is an example of,2')"/>
			        	<xsl:variable name="fieldsOfType" select="zenta:neighbours($doc,$type,'contains,1')"/>
			        	<xsl:variable name="aggregateFieldsOfType" select="zenta:neighbours($doc,$type,'aggregates,1')"/>
		        		<xsl:variable name="fieldList" select="
		        				zenta:extractFieldList($fieldsOfType,'contains')|
		        				zenta:extractFieldList($aggregateFieldsOfType,'aggregates')
		        				"/>
		        		<xsl:copy-of select="$fieldList[@id = $fieldsOfArtifact/@id or @typeId = $fieldsOfArtifact/@id]/(@field|@how)"/>
			        	<xsl:copy-of select="zenta:extractTestArtifact(.,$level+1)"/>
		        	</member>
		        </xsl:for-each>
		        </xsl:if>
		        <xsl:copy-of select="$element/documentation/text()"/>
	        </artifact>
	</xsl:function>
    <xsl:variable name="testData">
		<xsl:for-each select="//element[@xsi:type='TestData']">
            <testData>
                <xsl:copy-of select="@name"/>
                <xsl:attribute name="package" select="zenta:fullPackageName(/,zenta:neighbours(/,.,'contains,2'))"/>
                <xsl:for-each select="zenta:neighbours($doc,.,'contains,1')[@xsi:type='Test Artifact']">
					<xsl:copy-of select="zenta:extractTestArtifact(.,0)"/>
                </xsl:for-each>
            </testData>
		</xsl:for-each>
    </xsl:variable>

    <xsl:template match="/" mode="python">
    </xsl:template>

    <xsl:template match="/" mode="java">
        <xsl:for-each select="$testData//testData">
	<xsl:variable name="dependencies">
		<xsl:for-each select="distinct-values(.//@TestData[. != current()/@name])">
			<dependency name="{.}" varname="{concat(
				lower-case(substring(., 1, 1)), 
                substring(., 2))}"/>
		</xsl:for-each>
	</xsl:variable>
<xsl:result-document href="target/generated/test/java/{string-join(tokenize(@package,'\.'),'/')}/{@name}.java">package <xsl:value-of select="@package"/>;

import java.util.Set;

class <xsl:value-of select="@name"/> {

	<xsl:for-each select="artifact">
	public final <xsl:value-of select="@type"/>
		<xsl:text> </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:value-of select="if(text()) then concat(' = ',text()) else ''"/>;
	</xsl:for-each>

<xsl:text>
	</xsl:text>
	<xsl:for-each select="$dependencies/dependency">
	private <xsl:value-of select="concat(@name,' ', @varname)"/>
	</xsl:for-each>	
<xsl:text>
	</xsl:text>
	public <xsl:value-of select="@name"/>(<xsl:value-of select="string-join(
		for $dependency in $dependencies/dependency
			return concat(
				$dependency/@name,
				' ',
				$dependency/@varname
            )
		, ', '
		)"/>) {
		<xsl:variable name="testData" select="."/>
		<xsl:for-each select="artifact[member]">
			<xsl:value-of select="concat(@name,' = new ', @type, '();')"/><xsl:text>
		</xsl:text>			
			<xsl:variable name="artifact" select="."/>
			<xsl:for-each select="member">
				<xsl:value-of select="concat(
					$artifact/@name,
					'.set',
					upper-case(substring(@field,1,1)),
					substring(@field,2),
					'(',
					if(@how = 'aggregates') then 'Set.of(' else '',
					if(artifact/@TestData = $testData/@name)
						then
							''
						else
							concat(
								lower-case(substring(artifact/@TestData, 1, 1)), 
	                			substring(artifact/@TestData, 2),
	                			'.'
							),
					artifact/@name,
					if(@how = 'aggregates') then ')' else '',
					');')"/>
				<xsl:text>
		</xsl:text>
			</xsl:for-each>
		</xsl:for-each>
	}
}
</xsl:result-document>
        </xsl:for-each>
	</xsl:template>

</xsl:stylesheet>

