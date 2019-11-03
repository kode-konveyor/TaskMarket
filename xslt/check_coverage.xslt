<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE xml>
<xsl:stylesheet version="2.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:zenta="http://magwas.rulez.org/zenta"
	xmlns:zentatools="java:org.rulez.magwas.zentatools.XPathFunctions"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" version="1.0" encoding="utf-8"
		indent="yes" omit-xml-declaration="yes" />

	<xsl:include href="xslt/functions.xslt" />

    <xsl:variable name="jacoco" select="document('target/site/jacoco/jacoco.xml')"/>
    <xsl:variable name="javadoc" select="document('target/production/javadoc.xml')"/>
    <xsl:variable name="pit" select="/"/>

	<xsl:template match="/">
        <xsl:variable name="excluded">
            <xsl:for-each 
                select="$javadoc//method[annotation/@name='Generated' or annotation/@name='ExcludeFromCodeCoverage']/@qualified">
                <excludedMethod> <xsl:attribute name="name" select="string(.)"/> </excludedMethod>
            </xsl:for-each>
            <xsl:for-each 
                select="$javadoc//class[annotation/@name='Generated' or annotation/@name='ExcludeFromCodeCoverage']/@qualified">
                <excludedClass> <xsl:attribute name="name" select="string(.)"/> </excludedClass>
            </xsl:for-each>
        </xsl:variable>
        <xsl:variable name="unitCoverageMisses">
            <xsl:for-each 
                select="$jacoco//method[counter/@missed != 0]">
                <unitCoverageMiss>
                    <xsl:variable name="class" select="replace(../@name,'/','.')"/>
                    <xsl:attribute name="method" select="concat($class,'.',@name)"/>
                    <xsl:attribute name="class" select="$class"/>
                </unitCoverageMiss>
            </xsl:for-each>
        </xsl:variable>
        <xsl:variable name="mutationCoverageMisses">
            <xsl:for-each 
                select="$pit//mutation[@status != 'KILLED']">
                <mutationCoverageMiss>
                    <xsl:attribute name="method" select="concat(mutatedClass,'.',mutatedMethod)"/>
                    <xsl:attribute name="class" select="mutatedClass"/>
                </mutationCoverageMiss>
            </xsl:for-each>
        </xsl:variable>
        <xsl:result-document href="coverage-report.xml">
            <coverageReport>
                     <xsl:copy-of select="$excluded"/>
                     <xsl:copy-of select="$unitCoverageMisses"/>
                     <xsl:copy-of select="$mutationCoverageMisses"/>
            </coverageReport>
        </xsl:result-document>
        <xsl:for-each select="$unitCoverageMisses/*[not(@method=$excluded/excludedMethod/@name or @class=$excluded/excludedClass/@name)]">
ERROR: unit coverage miss at <xsl:value-of select="@method"/>
        </xsl:for-each>
        <xsl:for-each select="$mutationCoverageMisses/*[not(@method=$excluded/excludedMethod/@name or @class=$excluded/excludedClass/@name)]">
ERROR: mutation coverage miss at <xsl:value-of select="@method"/>
        </xsl:for-each>
        <xsl:for-each select="distinct-values($excluded/*[not(
                @name=$unitCoverageMisses//@method or
                @name=$unitCoverageMisses//@class or
                @name=$mutationCoverageMisses//@method or
                @name=$mutationCoverageMisses//@class
            )]/@name)">
ERROR: unnecessary coverage miss annotation at <xsl:value-of select="."/>
        </xsl:for-each>
<xsl:text>
</xsl:text>
	</xsl:template>

</xsl:stylesheet>

