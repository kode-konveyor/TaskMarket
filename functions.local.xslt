<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE xml>
<xsl:stylesheet
			xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
			version='2.0'
			xmlns:xhtml="http://www.w3.org/TR/xhtml1/transitional"
			xmlns:fn="http://www.w3.org/2005/xpath-functions"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:zenta="http://magwas.rulez.org/zenta">

    <xsl:variable name="doc" select="/"/>

	<xsl:function name="zenta:extractFieldList">
		<xsl:param name="fields"/>
		<xsl:param name="how"/>
       	<xsl:for-each select="$fields">
	       	<field>
	        	<xsl:attribute name="field" select="@name"/>
	       		<xsl:copy-of select="@id"/>
	        	<xsl:variable name="type" select="zenta:neighbours($doc,.,'is a/is the type of,1')"/>
	        	<xsl:attribute name="type" select="$type/@name"/>
	        	<xsl:attribute name="typeId" select="$type/@id"/>
	        	<xsl:attribute name="how" select="$how"/>
	       	</field>
       	</xsl:for-each>
	</xsl:function>
</xsl:stylesheet>
