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

    <xsl:variable name="services" select="zenta:gatherServices(/)"/>

	<xsl:variable name="dtos">
		<xsl:for-each select="//element[@xsi:type='DTO' and @template='false']">
		<dto>
			<xsl:copy-of select="@name"/>
			<xsl:attribute name="package" select="zenta:fullPackageName(/,zenta:neighbours(/,.,'contains,2'))"/>
			<xsl:copy-of select="
		        				zenta:extractFieldList(zenta:neighbours($doc,.,'contains,1'),'contains')|
		        				zenta:extractFieldList(zenta:neighbours($doc,.,'aggregates,1'),'aggregates')
		        				"/>
		</dto>
		</xsl:for-each>
	</xsl:variable>

	<xsl:variable name="entities">
		<xsl:for-each select="//element[@xsi:type='Entity' and @template='false']">
		<dto>
			<xsl:copy-of select="@name"/>
			<xsl:attribute name="package" select="zenta:fullPackageName(/,zenta:neighbours(/,.,'contains,2'))"/>
			<xsl:copy-of select="
		        				zenta:extractFieldList(zenta:neighbours($doc,.,'contains,1'),'contains')|
		        				zenta:extractFieldList(zenta:neighbours($doc,.,'aggregates,1'),'aggregates')
		        				"/>
		</dto>
		</xsl:for-each>
	</xsl:variable>

    <xsl:template match="/" mode="python">
    </xsl:template>

	<xsl:template match="/" mode="java">
		<xsl:for-each select="$dtos/dto">
			<xsl:result-document href="target/generated/main/java/{string-join(tokenize(@package,'\.'),'/')}/{@name}.java" method="text">
<xsl:value-of select="concat('package ',@package,';')"/>

@Data
class <xsl:value-of select="@name"/>() {
	<xsl:for-each select="field">
	private <xsl:value-of select="concat(if(@how='aggregates') then concat('Set',codepoints-to-string(60),@type,'>') else @type ,' ',@field)"/>;
	</xsl:for-each>
}
</xsl:result-document>
		</xsl:for-each>
		<xsl:for-each select="$entities/dto">
			<xsl:result-document href="target/generated/main/java/{string-join(tokenize(@package,'\.'),'/')}/{@name}.java" method="text">
<xsl:value-of select="concat('package ',@package,';')"/>
<xsl:copy-of select="."/>

@Data
@Entity
class <xsl:value-of select="@name"/>() {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	<xsl:for-each select="field">
	<xsl:if test="@how = 'aggregates'">
	@ManyToMany</xsl:if>
	private <xsl:value-of select="concat(if(@how='aggregates') then concat('Set',codepoints-to-string(60),@type,'>') else @type ,' ',@field)"/>;
	</xsl:for-each>
}
</xsl:result-document>
		</xsl:for-each>
        <xsl:for-each select="$services">
	        <xsl:variable name="params">
	            <xsl:for-each select="param">
	                <param>
	                <xsl:attribute name="p" select="concat(@type, ' ', @name)"/>
	                </param>
	            </xsl:for-each>
	        </xsl:variable>
<xsl:result-document href="target/generated/main/java/{string-join(tokenize(@package,'\.'),'/')}/{@name}.java">
package <xsl:value-of select="@package"/>

class <xsl:value-of select="@name"/> {

    <xsl:value-of select="@type"/> call (<xsl:value-of select="string-join($params//@p, ', ')"/>)
}
</xsl:result-document>
        </xsl:for-each>
	</xsl:template>

</xsl:stylesheet>

