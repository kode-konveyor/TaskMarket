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

	<xsl:param name="modelbasename" />

	<xsl:variable name="rich" select="document(concat($modelbasename,'.rich'))"/>

	<xsl:function name="zenta:getAnnotation">
		<xsl:param name="context" />
		<xsl:param name="annotationName" />
		<xsl:copy-of
			select="normalize-space(
			if ($context/annotation[@name=$annotationName])
			then
				$context/annotation[@name=$annotationName]
			else
				$context/../annotation[@name=$annotationName]/argument/value/text())" />
	</xsl:function>

	<xsl:template
		match="@*|*|processing-instruction()|comment()|text()" mode="#all">
		<xsl:apply-templates
			select="*|@*|text()|processing-instruction()|comment()"
			mode="#current" />
	</xsl:template>

	<xsl:template match="
		method[
			annotation/@name='Test' or 
			annotation/@name='ContractRule' or 
			annotation/@name='ReturnDetail']"
		mode="gatherTestCases">
		<testcase>
			<xsl:attribute name="name" select="annotation[@name='DisplayName']/argument/value" />
			<xsl:attribute name="doc"
				select="
				if(annotation/@name='ContractRule' or annotation/@name='ReturnDetail')
				then
					annotation[@name='ContractRule' or @name='ReturnDetail']/argument/value/text()
				else
					()" />
			<xsl:variable name="behaviour"
				select="zenta:getAnnotation(.,'TestedBehaviour')" />
			<xsl:variable name="service"
				select="zenta:getAnnotation(.,'TestedService')" />
			<xsl:attribute name="behaviour" select="$behaviour" />
			<xsl:attribute name="service" select="$service" />
		</testcase>
	</xsl:template>


	<xsl:template match="/" mode="gatherTestCases">
		<testcases>
			<xsl:apply-templates
				select="*|text()|processing-instruction()|comment()" mode="#current" />
		</testcases>
	</xsl:template>

	<xsl:function name="zenta:listBehaviours">
		<xsl:param name="testcases" />
		<xsl:param name="service" />
		<xsl:param name="behaviour" />
		<xsl:for-each
			select="distinct-values($testcases//testcase[@service=$service and @behaviour=$behaviour]//@behaviour)">
			<xsl:variable name="behaviour" select="." />
				<xsl:copy-of
					select="$testcases//testcase[@service=$service and @behaviour=$behaviour]" />
		</xsl:for-each>
	</xsl:function>

	<xsl:function name="zenta:listServices">
		<xsl:param name="testcases" />
		<xsl:param name="service" />
		<xsl:for-each
			select="distinct-values($testcases//testcase[@service=$service]//@behaviour)">
			<xsl:variable name="behaviour" select="." />
			<behaviour>
				<xsl:attribute name="name" select="$behaviour" />
				<xsl:copy-of
					select="zenta:listBehaviours($testcases,$service,$behaviour)" />
			</behaviour>
		</xsl:for-each>
	</xsl:function>

	<xsl:function name="zenta:listBehaviours">
		<xsl:param name="testcases" />
		<features>
			<xsl:for-each
				select="distinct-values($testcases//@service)">
				<xsl:variable name="service" select="." />
				<feature>
                    <xsl:variable name="step" select="zenta:neighbours($rich,$rich//element[@name=$service],'is implemented by/implements,2')"/>
					<xsl:attribute name="name" select="$step/@name" />
					<xsl:attribute name="service" select="$service" />
                    <xsl:copy-of select="$step/documentation"/>
					<xsl:copy-of
						select="zenta:listServices($testcases,$service)" />
				</feature>
			</xsl:for-each>
		</features>
	</xsl:function>

	<xsl:template match="/">
		<xsl:variable name="testcases">
			<xsl:apply-templates select="/"
				mode="gatherTestCases" />
		</xsl:variable>
		<xsl:variable name="behaviours">
			<xsl:copy-of select="zenta:listBehaviours($testcases)" />
		</xsl:variable>
		<xsl:result-document
			href="shippable/{$modelbasename}-implementedTestCases.xml">
			<xsl:copy-of select="$testcases" />
		</xsl:result-document>
		<xsl:result-document
			href="shippable/{$modelbasename}-implementedBehaviours.xml">
			<xsl:copy-of select="$behaviours" />
		</xsl:result-document>
		<xsl:result-document
			href="shippable/{$modelbasename}-implementedBehaviours.docbook">
			<xsl:apply-templates select="$behaviours"
				mode="behaviourDocbook" />
		</xsl:result-document>
	</xsl:template>

	<xsl:template match="features" mode="behaviourDocbook">
		<article version="5.0">
			<title>Implemented behaviours</title>
			<xsl:apply-templates select="*" mode="#current" />
		</article>
	</xsl:template>

	<xsl:template match="feature|behaviour"
		mode="behaviourDocbook">
		<section>
			<xsl:variable name="titlestring"
				select="concat(node-name(.),': ',@name)" />
			<xsl:attribute name="id" select="$titlestring" />
			<title>
				<xsl:value-of select="$titlestring" />
			</title>
            <xsl:if test="@service">
                <para>Implemented in <xsl:value-of select="@service"/></para>
            </xsl:if>
            <itemizedlist>
			<xsl:apply-templates select="*" mode="#current" />
            </itemizedlist>
		</section>
	</xsl:template>

	<xsl:template match="testcase" mode="behaviourDocbook">
		<listitem>
			<para>
				<xsl:value-of select="@name" />
			</para>
		</listitem>
	</xsl:template>

</xsl:stylesheet>

