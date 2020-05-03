<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version='2.0'
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zenta="http://magwas.rulez.org/zenta">


	<xsl:function name="zenta:exactlyOne">
		<xsl:param name="selected"/>
		<xsl:param name="errmsg"/>
		<xsl:copy-of select="
			if(count($selected)=1)
				then ()
				else if (count($selected) = 0)
				then $errmsg
				else (string-join($selected/concat(@name,': ',@id),' , '))
		"/>
	</xsl:function>

</xsl:stylesheet>
