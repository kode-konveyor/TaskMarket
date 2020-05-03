<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
    xmlns:zenta="http://magwas.rulez.org/zenta"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zentatools="http://magwas.rulez.org/zentatools"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes" omit-xml-declaration="yes"/>

	<xsl:param name="debug" select="'false'"/>
<xsl:include href="xslt/functions.xslt"/>
<xsl:include href="xslt/docbook.local.xslt"/>

<xsl:function name="zenta:createElemList">
	<xsl:param name="file"/>
	<xsl:param name="namepath"/>
	<xsl:param name="basepath"/>
	<xsl:param name="valuepath"/>
	<xsl:variable name="model" select="document($file)"/>
	<xsl:for-each select="$model">
		<xsl:variable name="bases" select="zentatools:evaluate($basepath)"/>
		<xsl:for-each select="$bases">
	 		<xsl:variable name="name" select="zentatools:evaluate($namepath)"/>
	 		<xsl:variable name="object" select="."/>
	 		<xsl:for-each select="zentatools:evaluate($valuepath)">
	 			<entry>
	 				<xsl:attribute name="name" select="$name"/>
	 				<xsl:attribute name="value" select="."/>
	 				<object>
	 					<xsl:copy-of select="$object"/>
	 				</object>
	 				<value>
		 				<xsl:copy-of select="."/>
	 				</value>
	 			</entry>
	 		</xsl:for-each>
		</xsl:for-each>
	</xsl:for-each>
</xsl:function>

	<xsl:function name="zenta:wrapForEval">
		<xsl:param name="xpath"/>
		<xsl:param name="inmodel"/>
		<xsl:param name="ininput"/>
		<xsl:param name="doc"/>
		<xsl:param name="error"/>
		<xsl:param name="errorId"/>
		<xsl:param name="entryName"/>
		<xsl:copy-of select="zentatools:evaluate($xpath)"/>
	</xsl:function>
	
	<xsl:template match="/">
		<consistencycheck>
			<xsl:apply-templates select="//check|//globalcheck"/>
		</consistencycheck>
	</xsl:template>
  <xsl:template match="check">
  		<data>
  			<xsl:variable name="inmodel" select="zenta:createElemList(@modelfile,@modelnamepath,@modelbasepath,@modelvaluepath)"/>
  			<xsl:variable name="ininput" select="zenta:createElemList(@inputfile,@inputnamepath,@inputbasepath,@inputvaluepath)"/>
  			<xsl:variable name="doc" select="document(@referencemodel)"/>
  			<xsl:variable name="inputerrorid" select="@inputerrorid"/>
  			<xsl:variable name="modelerrorid" select="@modelerrorid"/>
  			<xsl:variable name="errorURL" select="@errorURL"/>
  			<xsl:variable name="errorTitle" select="@errortitlestring"/>
  			<xsl:variable name="errorDescription" select="@errordescription"/>
  			<xsl:if test="$debug='true'">
		  		<model>
		  			<xsl:copy-of select="$inmodel"/>
		  		</model>
		  		<input>
		  			<xsl:copy-of select="$ininput"/>
		  		</input>
  			</xsl:if>
	  		<xsl:copy-of select="."/>
	  		<onlymodel>
	  			<xsl:for-each select="$inmodel">
	  				<xsl:if test="count($ininput[@name=current()/@name and @value=current()/@value]) =0 ">
						<xsl:variable name="element" select="$doc//element[@id=current()//error/@element]"/>
						<xsl:variable name="isFuture" select="zenta:neighbours($doc,$element,'implements,2')[@xsi:type = 'Milestone'] and not(zenta:neighbours($doc,$element,'implements,2;contains,2')[@xsi:type = 'Project']) "/>
						<xsl:variable name="entryName" select="if($isFuture and @name='constraintError')
						then
							'constraintWarning'
						else @name
						 "/>
	  					<entry>
	  						<xsl:attribute name="name" select="$entryName"/>
	  						<xsl:copy-of select="@value"/>
	  						<xsl:variable name="error" select="."/>
	  						<xsl:variable name="errorID" select="zenta:wrapForEval($modelerrorid,$inmodel,$ininput,$doc,.,'',$entryName)"/>
	  						<xsl:attribute name="errorID" select="$errorID"/>
	  						<xsl:attribute name="errorURL" select="zenta:wrapForEval($errorURL,$inmodel,$ininput,$doc,.,$errorID,$entryName)"/>
	  						<xsl:attribute name="errorTitle" select="zenta:wrapForEval($errorTitle,$inmodel,$ininput,$doc,.,$errorID,$entryName)"/>
							<xsl:variable name="details" select="zenta:wrapForEval($errorDescription,$inmodel,$ininput,$doc,.,$errorID,$entryName)"/>
	  						<errorDescription>
		  						<xsl:copy-of select="$details"/>
	  						</errorDescription>
							<xsl:message>onlymodel:<xsl:value-of select="$details"/></xsl:message>
	  					</entry>
	  				</xsl:if>
	  			</xsl:for-each>
	  		</onlymodel>
	  		<onlyinput>
	  			<xsl:for-each select="$ininput">
	  				<xsl:if test="count($inmodel[@name=current()/@name and @value=current()/@value]) =0 ">
	  					<entry>
	  						<xsl:copy-of select="@name|@value"/>
	  						<xsl:variable name="errorID" select="zenta:wrapForEval($inputerrorid,$inmodel,$ininput,$doc,.,'',@name)"/>
	  						<xsl:attribute name="errorID" select="$errorID"/>
	  						<xsl:attribute name="errorURL" select="zenta:wrapForEval($errorURL,$inmodel,$ininput,$doc,.,$errorID,@name)"/>
	  						<xsl:attribute name="errorTitle" select="zenta:wrapForEval($errorTitle,$inmodel,$ininput,$doc,.,$errorID,@name)"/>
							<xsl:variable name="details" select="zenta:wrapForEval($errorDescription,$inmodel,$ininput,$doc,.,$errorID,@name)"/>
	  						<errorDescription>
		  						<xsl:copy-of select="zenta:wrapForEval($errorDescription,$inmodel,$ininput,$doc,.,$errorID,@name)"/>
	  						</errorDescription>
							<xsl:message>onlyinput:<xsl:value-of select="@name"/>/<xsl:value-of select="@value"/>.<xsl:value-of select="$details"/></xsl:message>
	  					</entry>
	  				</xsl:if>
	  			</xsl:for-each>
	  		</onlyinput>
  		</data>
  </xsl:template>

  <xsl:template match="globalcheck">
  		<data>
  			<xsl:variable name="id" select="@id"/>
  			<xsl:variable name="inputfile" select="@inputfile"/>
  			<xsl:variable name="doc" select="document(@inputfile)"/>
  			<xsl:variable name="check" select="@check"/>
  			<xsl:variable name="errorid" select="@errorid"/>
  			<xsl:variable name="errorURL" select="@errorURL"/>
  			<xsl:variable name="errorTitle" select="@errortitlestring"/>
  			<xsl:variable name="errorDescription" select="@errordescription"/>
	  		<xsl:copy-of select="."/>
			<xsl:choose>
				<xsl:when test="not(exists($doc))">
 					<error>
						<xsl:attribute name="errorID" select="concat('nonexisting_',$id)"/>
						<xsl:attribute name="errorTitle" select="concat('The file to check is missing in globalcheck ',$id)"/>
						<errorDescription>
							missing file: 
							<xsl:value-of select="$inputfile"/>
						</errorDescription>
 					</error>
 				</xsl:when>
 				<xsl:otherwise>
		  			<xsl:for-each select="$doc">
		  				<xsl:if test="zentatools:evaluate($check)">
		  					<error>
		  						<xsl:variable name="errorID" select="zentatools:evaluate($errorid)"/>
		  						<xsl:attribute name="errorID" select="$errorID"/>
		  						<xsl:attribute name="errorURL" select="zentatools:evaluate($errorURL)"/>
		  						<xsl:variable name="errorTitle" select="zentatools:evaluate($errorTitle)"/>
		  						<xsl:attribute name="errorTitle" select="$errorTitle"/>
		  						<errorDescription>
			  						<xsl:copy-of select="zentatools:evaluate($errorDescription)"/>
		  						</errorDescription>
			  					<xsl:message><xsl:value-of select="$inputfile"/>/<xsl:value-of select="$errorTitle"/>.</xsl:message>
					  		</error>
		  				</xsl:if>
 						</xsl:for-each>
 				</xsl:otherwise>
 				</xsl:choose>
	  	</data>
  </xsl:template>

</xsl:stylesheet>

