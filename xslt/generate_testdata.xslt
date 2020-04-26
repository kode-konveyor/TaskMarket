<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE xml>
<xsl:stylesheet version="2.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zenta="http://magwas.rulez.org/zenta"
	xmlns:zentatools="http://magwas.rulez.org/zentatools"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" version="1.0" encoding="utf-8"
		indent="yes" omit-xml-declaration="yes" />

	<xsl:include href="xslt/functions.xslt" />

	<xsl:param name="outputbase" />

	<xsl:function name="zenta:makeSnakeCase">
		<xsl:param name="name" />
		<xsl:value-of
			select="
		string-join(
			for $tag in tokenize($name)
				return upper-case($tag),
			'_'
		)
		" />
	</xsl:function>
	<xsl:function name="zenta:makeVariableName">
		<xsl:param name="name" />
		<xsl:value-of
			select="zenta:deCapitalize(zenta:makeTypeName($name))" />
	</xsl:function>

	<xsl:function name="zenta:deCapitalize">
		<xsl:param name="str" />
		<xsl:value-of
			select="concat(lower-case(substring($str,1,1)),substring($str, 2))" />
	</xsl:function>

	<xsl:function name="zenta:makeTypeName">
		<xsl:param name="name" />
		<xsl:value-of
			select="
				string-join(
					for $tag in tokenize($name)
					return
						zenta:capitalize($tag),
					''
				)
		" />
	</xsl:function>

	<xsl:function name="zenta:extractTestArtifact">
		<xsl:param name="element" />
		<xsl:param name="level" />

		<artifact>
			<xsl:copy-of select="$element/@name" />
			<xsl:attribute name="TestData"
				select="zenta:neighbours($doc,$element,'contains,2')/@name" />
			<xsl:variable name="types"
				select="zenta:neighbours($doc,$element, 'is a/is the type of,1')" />
			<xsl:variable name="type"
				select="
		        	if($types[@xsi:type='External type'])
		        	 then $types[@xsi:type='External type']
		        	 else $types
		        	" />
			<xsl:attribute name="type" select="$type/@name" />
			<xsl:copy-of select="$type/@xsi-type" />
			<xsl:copy-of select="$type" />
			<xsl:copy-of select="$element/documentation/text()" />
		</artifact>
	</xsl:function>

	<xsl:variable name="testData">
		<xsl:for-each select="//element[@xsi:type='Test Data']">
			<testData>
				<xsl:copy-of select="@name" />
				<xsl:attribute name="package"
					select="zenta:fullPackageName(/,zenta:neighbours(/,.,'contains,2'))" />
				<xsl:copy-of
					select="
                	for $getter in zenta:neighbours($doc,.,'contains,1')[@name='get()']
                	return zenta:neighbours($doc,$getter,'has an example as/is an example of,2')
                " />
				<xsl:for-each
					select="zenta:neighbours($doc,.,'contains,1')[@xsi:type='Test Artifact']">
					<xsl:copy-of select="zenta:extractTestArtifact(.,0)" />
				</xsl:for-each>
			</testData>
		</xsl:for-each>
	</xsl:variable>

	<xsl:template match="/" mode="python">
	</xsl:template>

	<xsl:function name="zenta:generateEntityGetter">
		<xsl:param name="a" />
		<xsl:param name="baseName" />
		<xsl:variable name="typeName"
			select="concat($baseName,'EntityTestData')" />
		<xsl:variable name="tdName"
			select="concat(zenta:deCapitalize($baseName),'Entity')" />
		<xsl:text> {
		</xsl:text>
		<xsl:value-of select="$baseName" />
		<xsl:text>Entity </xsl:text>
		<xsl:value-of select="$tdName" />
		<xsl:text> = new </xsl:text>
		<xsl:value-of select="$baseName" />
		<xsl:text>Entity();
		</xsl:text>
		<xsl:value-of select="$tdName" />
		<xsl:text>.setId(</xsl:text>
		<xsl:value-of select="concat($baseName,'TestData')" />
		<xsl:text>.ID);
		</xsl:text>
		<xsl:for-each
			select="$a/element/value[@ancestorName='has' or @ancestorName='aggregates']">
			<xsl:value-of select="$tdName" />
			<xsl:text>.set</xsl:text>
			<xsl:value-of select="zenta:makeTypeName(@name)" />
			<xsl:text>(</xsl:text>
			<xsl:variable name="descendants"
				select="//element[@id=current()/@target]/value[@direction=1 and (@ancestorName='has' or @ancestorName='aggregates')]" />
			<xsl:copy-of
				select="
				if ($descendants)
					then if(@ancestorName='aggregates')
						then concat('Set.of(',zenta:makeTypeName(@name),'EntityTestData.get())')
						else concat(zenta:makeTypeName(@name),'EntityTestData.get()')
					else concat($baseName,'TestData.',zenta:makeSnakeCase(@name))" />
			<xsl:text>);
		</xsl:text>
		</xsl:for-each>
		<xsl:text>
		return </xsl:text>
		<xsl:value-of select="$tdName" />
		<xsl:text>;
	}</xsl:text>
	</xsl:function>

	<xsl:function name="zenta:generateDTOGetter">
		<xsl:param name="a" />
		<xsl:param name="baseName" />
		<xsl:variable name="typeName"
			select="concat($baseName,'DTOTestData')" />
		<xsl:variable name="tdName"
			select="concat(zenta:deCapitalize($baseName),'DTO')" />
		<xsl:text> {
		</xsl:text>
		<xsl:value-of select="$baseName" />
		<xsl:text>DTO </xsl:text>
		<xsl:value-of select="$tdName" />
		<xsl:text> = new </xsl:text>
		<xsl:value-of select="$baseName" />
		<xsl:text>DTO();
		</xsl:text>
		<xsl:value-of select="concat($tdName,'.setId(')" />
		<xsl:value-of select="concat($baseName,'TestData')" />
		<xsl:text>.ID);
		</xsl:text>
		<xsl:for-each
			select="$a/element/value[@ancestorName='has' or @ancestorName='aggregates']">
			<xsl:value-of select="$tdName" />
			<xsl:text>.set</xsl:text>
			<xsl:value-of select="zenta:makeTypeName(@name)" />
			<xsl:text>(</xsl:text>
			<xsl:variable name="descendants"
				select="//element[@id=current()/@target]/value[@direction=1 and (@ancestorName='has' or @ancestorName='aggregates')]" />
			<xsl:copy-of
				select="
				if ($descendants)
					then if(@ancestorName='aggregates')
						then concat(zenta:makeTypeName(@name),'TestData.ID)')
						else concat(zenta:makeTypeName(@name),'EntityTestData.get()')
					else concat($baseName,'TestData.',zenta:makeSnakeCase(@name))" />
			<xsl:text>);
		</xsl:text>
		</xsl:for-each>
		<xsl:text>
		return </xsl:text>
		<xsl:value-of select="$tdName" />
		<xsl:text>;
	}</xsl:text>
	</xsl:function>


	<xsl:template match="/" mode="java">
		<xsl:for-each select="$testData//testData">
			<xsl:variable name="dependencies">
				<xsl:for-each
					select="distinct-values(.//@TestData[. != current()/@name])">
					<dependency name="{.}"
						varname="{concat(
					lower-case(substring(., 1, 1)), 
	                substring(., 2))}" />
				</xsl:for-each>
			</xsl:variable>
<xsl:result-document
	href="target/generated/test/java/{string-join(tokenize(@package,'\.'),'/')}/{@name}.java">package <xsl:value-of select="@package" />;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class <xsl:value-of select="@name" /> {

	public final static Long ID = <xsl:value-of select="sum(string-to-codepoints(@name))" />L;
	<xsl:for-each select="artifact[text()]">
	public final static <xsl:value-of select="zenta:makeTypeName(@type)" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="@name" />
		<xsl:copy-of
			select="if(text()) then concat(' = ',text()) else 'error!'" />;
	</xsl:for-each>
}
</xsl:result-document>
			<xsl:variable name="baseName"
				select="substring-before(@name,'TestData')" />
<xsl:result-document
	href="target/generated/test/java/{string-join(tokenize(@package,'\.'),'/')}/{$baseName}EntityTestData.java">package <xsl:value-of select="@package" />;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class <xsl:value-of select="$baseName" />EntityTestData {
	<xsl:for-each select="artifact[count(text())=0]">
	public final static <xsl:value-of select="$baseName" />
		<xsl:text>Entity </xsl:text>
		<xsl:value-of select="@name" />
		<xsl:copy-of
			select="zenta:generateEntityGetter(.,$baseName)" />;
	</xsl:for-each>
}
</xsl:result-document>
<xsl:result-document
	href="target/generated/test/java/{string-join(tokenize(@package,'\.'),'/')}/{$baseName}DTOTestData.java">package <xsl:value-of select="@package" />;

import java.util.Set;

import javax.annotation.Generated;

@Generated("by zenta-tools")
public class <xsl:value-of select="$baseName" />DTOTestData {

	<xsl:for-each select="artifact[count(text())=0]">
	public final static <xsl:value-of select="$baseName" />
		<xsl:text>DTO </xsl:text>
		<xsl:value-of select="@name" />
		<xsl:copy-of
			select="zenta:generateDTOGetter(.,$baseName)" />;
	</xsl:for-each>

}
</xsl:result-document>

<xsl:result-document
	href="target/generated/main/java/{string-join(tokenize(@package,'\.'),'/')}/{$baseName}DTO.java">
package <xsl:value-of select="@package" />;

import java.util.Set;

import javax.annotation.Generated;

import lombok.Data;

@Generated("by zenta-tools")
@Data
public class <xsl:value-of select="$baseName" />DTO {
	<xsl:text>private Long id;
	</xsl:text>
				<xsl:for-each
					select="zenta:neighbours($doc,element,'has,1'),zenta:neighbours($doc,element,'aggregates,1')">
		<xsl:variable name="aggregate"
			select="value[zenta:makeTypeName(@name)=$baseName and @direction=2 and @ancestorName='aggregates']" />
		<xsl:variable name="types"
			select="zenta:neighbours($doc,., 'has an example as/is an example of,1;is a/is the type of,1')" />
		<xsl:variable name="type"
			select="
     	if($types[@xsi:type='External type'])
     	 then $types[@xsi:type='External type']/@name
     	 else 'Long'
     	" />
		<xsl:variable name="typeName"
			select="
     	if($aggregate)
     	then concat('Set&lt;',zenta:makeTypeName($type),'>')
     	else zenta:makeTypeName($type)
     " />
	<xsl:text>private </xsl:text>
		<xsl:value-of disable-output-escaping="yes"
			select="$typeName" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="zenta:makeVariableName(@name)" />
					<xsl:text>;
	</xsl:text>
				</xsl:for-each>
}
</xsl:result-document>

			<xsl:result-document
				href="target/generated/main/java/{string-join(tokenize(@package,'\.'),'/')}/{$baseName}Entity.java">package <xsl:value-of select="@package" />;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Generated("by zenta-tools")
@Data
@Entity
public class <xsl:value-of select="$baseName" />Entity {
	<xsl:text>@Id
  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
</xsl:text>
		<xsl:for-each
			select="zenta:neighbours($doc,element,'has,1'),zenta:neighbours($doc,element,'aggregates,1')">
			<xsl:variable name="aggregate"
				select="value[zenta:makeTypeName(@name)=$baseName and @direction=2 and @ancestorName='aggregates']" />
			<xsl:variable name="types"
				select="zenta:neighbours($doc,., 'has an example as/is an example of,1;is a/is the type of,1')" />
			<xsl:variable name="type"
				select="
      	if($types[@xsi:type='External type'])
      	 then $types[@xsi:type='External type']/@name
      	 else concat(@name,'Entity')
      	" />
			<xsl:variable name="typeName"
				select="
      	if($aggregate)
      	then concat('Set&lt;',zenta:makeTypeName($type),'>')
      	else zenta:makeTypeName($type)
      " />
			<xsl:text>	private </xsl:text>
			<xsl:value-of disable-output-escaping="yes"
				select="$typeName" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="zenta:makeVariableName(@name)" />
					<xsl:text>;
</xsl:text>
				</xsl:for-each>
}
</xsl:result-document>

		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>

