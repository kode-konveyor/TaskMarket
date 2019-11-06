<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:zenta="http://magwas.rulez.org/zenta"
	xmlns:zentatools="java:org.rulez.magwas.zentatools.XPathFunctions"
    xmlns:saxon="http://saxon.sf.net/"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes" omit-xml-declaration="yes"/>


    <xsl:function name="zenta:modelErrorTitle">
    	<xsl:param name="object"/>
    	<xsl:param name="doc"/>
    	<xsl:value-of select="if ($object/object/error/@type='minOccursError')
        	then concat(
        		'missing relation for ',
	        	$doc//element[@id=$object/object/error/@element]/@name
	        	)
	        else if ($object/object/error/@type='maxOccursError')
        	then concat(
        		'extra relation for ',
	        	$doc//element[@id=$object/object/error/@element]/@name
	        	)
	        else if ($object/object/error/@type='constraintError')
        	then concat(
        		'failing constraint for ',
	        	$doc//element[@id=$object/object/error/@element]/@name
	        	)
			else concat('unknown error type ', $object/object/error/@type)
    	"/>
    </xsl:function>
    
    <xsl:function name="zenta:describeConstraintError">
    	<xsl:param name="element"/>
    	<xsl:param name="errobj"/>
    	<xsl:param name="doc"/>
    	<xsl:param name="errorName"/>
    	
    	<xsl:value-of select="$errorName"/>:
    	<link linkend='{$element/@id}'>
    	<xsl:value-of select="$element/@name"/>
    	</link>
        does not satisfy constraint
        <xsl:copy-of select="string($errobj/@constraintName)"/>
        (<xsl:copy-of select="string($errobj/@constraintXPath)"/>).
    </xsl:function>

    <xsl:function name="zenta:describeOccurs">
    	<xsl:param name="element"/>
    	<xsl:param name="errobj"/>
    	<xsl:param name="doc"/>
    	<xsl:if test="not($errobj/@type = 'minOccursError' or
    		$errobj/@type = 'maxOccursError')">
    		<xsl:message terminate="yes">
    			unknown element type: <xsl:value-of select="$errobj/@type"/> in
    			<f>
    			<xsl:copy-of select="$element"/>
    			errobj: <xsl:copy-of select="$errobj"/>
    			</f>
    		</xsl:message>
    	</xsl:if>
        <xsl:variable name="ancestorids" select="zenta:getRelationDescentry($doc//connection[@id=$errobj/@id and @direction=$errobj/@direction],$errobj/@direction,$doc)/@id"/>
    	<xsl:variable name="relations">
    		<xsl:for-each select="$element/value[@ancestor=$ancestorids]">
	    		<link linkend="{@target}">
	    			<xsl:value-of select="@name"/>
	    		</link>
	    		<xsl:if test="position() != last()">
	    			and
	    		</xsl:if>
    		</xsl:for-each>
    	</xsl:variable>
    	<xsl:variable name="relationcount" select="count($relations/link)"/>
    	<link linkend='{$element/@id}'>
    	<xsl:value-of select="$element/@name"/>
    	</link>
    	<xsl:value-of select="if ($errobj/@type = 'minOccursError')
    		then
    			' should have at least '
    		else
    			' should have only '
    	"/>
    	<xsl:value-of select="if ($errobj/@type = 'minOccursError')
    		then
    			$errobj/@minOccurs
    		else
    			$errobj/@maxOccurs
    	"/>
    	<xsl:text> </xsl:text>
    	<link linkend='{$errobj/@id}'>
    	<xsl:value-of select="$errobj/@name"/>
    	</link>
    	<xsl:text> relation, </xsl:text>
    	<xsl:value-of select="if ($errobj/@type = 'maxOccursError')
    		then
    			'but already have '
    		else if ($relationcount > 0 )
    		then
    			'but have only '
    		else if($errobj/@direction='1')
    			then
    				'but have none outgoing'
    			else
    				'but have none incoming'
    	"/>
    	<xsl:if test="$relationcount > 0 ">
	    	<xsl:value-of select="$relationcount"/>
	    	<xsl:value-of select="if($errobj/@direction='1')
	    		then
	    			' to '
	    		else
	    			' from '"/>
	    	<xsl:copy-of select="$relations"/>
    	</xsl:if>
    </xsl:function>
    <xsl:function name="zenta:modelErrorDescription">
    	<xsl:param name="object"/>
    	<xsl:param name="doc"/>
    	<xsl:param name="errorName"/>
    	<xsl:variable name="errobj" select="$object/object/error"/>
        <xsl:choose>
          <xsl:when test="$errobj/@type = 'minOccursError' or
            $errobj/@type = 'maxOccursError'">
    	    <xsl:copy-of select="
            	zenta:describeOccurs(
            		$doc//element[@id=$errobj/@element],
            		$errobj,
            		$doc)
        	"/>
          </xsl:when>
          <xsl:when test="$errobj/@type = 'constraintError'">
    	    <xsl:copy-of select="
            	zenta:describeConstraintError(
            		$doc//element[@id=$errobj/@element],
            		$errobj,
            		$doc,$errorName)
        	"/>
          </xsl:when>
          <xsl:otherwise>
    		<xsl:message terminate="yes">
    			unknown element type: <xsl:value-of select="$errobj/@type"/> in
    			<f>
    			<xsl:copy-of select="$object"/>
    			errobj: <xsl:copy-of select="$errobj"/>
    			</f>
    		</xsl:message>
          </xsl:otherwise>
        </xsl:choose>
    </xsl:function>

	<xsl:function name="zenta:listIssues">
		<xsl:param name="errissues"/>
		<xsl:apply-templates select="$errissues" mode="deviations"/>
	</xsl:function>

	<xsl:function name="zenta:errorIssue">
		<xsl:param name="entry"/>
		<xsl:param name="issues"/>
		<xsl:variable name="errissues" select="$issues//link[@url=$entry/@errorURL]/.."/>
		<xsl:choose>
			<xsl:when test="$errissues">
				<xsl:copy-of select="zenta:listIssues($errissues)"/>
			</xsl:when>
			<xsl:otherwise>
				<para>no related issues in tracker</para>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:function>

</xsl:stylesheet>
