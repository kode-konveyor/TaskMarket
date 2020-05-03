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
	<xsl:include href="xslt/lib/fullpackage.xslt" />

	<xsl:param name="modelbasename" />
	<xsl:param name="reponame" />
	<xsl:param name="github_org" />

    <xsl:variable name="zenta" select="document(concat($modelbasename,'.zenta'))"/>
    <xsl:variable name="implemented" select="document('shippable/implementedBehaviours.xml')"/>
    <xsl:variable name="issues" select="document('inputs/issues.xml')"/>
    <xsl:variable name="rich" select="/"/>

    <xsl:function name="zenta:camelCase">
	    <xsl:param name="text"/>
	    <xsl:value-of select="string-join(for $word in tokenize($text,' ') return concat(upper-case(substring($word,1,1)),
          substring($word, 2),
          ' '[not(last())]
	  ),'') "/>
    </xsl:function>
	<xsl:template match="/">
		<xsl:variable name="behaviours">
			<tasks>
				<xsl:call-template name="behaviours" />
			</tasks>
		</xsl:variable>
		<xsl:result-document
			href="shippable/behaviours.txt">
			<xsl:copy-of
				select="zenta:writeTestcasesAsText($behaviours)" />
		</xsl:result-document>
		<xsl:result-document
			href="shippable/behaviours.xml">
			<xsl:copy-of select="$behaviours" />
		</xsl:result-document>
        <xsl:copy-of select="
        codepoints-to-string(10),
        $behaviours//task/concat(
            @status,
            substring(':                  ',1,20 - string-length(@status)),
            @service,'/',
            @behaviour,' ',
            @issueUrl,
            codepoints-to-string(10)),
        codepoints-to-string(10)
        "/>
	</xsl:template>


    <xsl:function name="zenta:drawpic">
        <xsl:param name="picid"/>
            <xsl:variable name="picname" select="$zenta//element[@id=$picid]/@name"/>
            <xsl:variable name="piclink" select="concat(
                'https://repository.kodekonveyor.com/',
               $reponame,
               '/',
               $github_org,
               '/develop/pics/',
               $picid,
               '.png')"/>
            <xsl:variable name="doclink" select="concat(
                'https://repository.kodekonveyor.com/',
               $reponame,
               '/',
               $github_org,
               '/develop/index.html#',
               $picid)"/>
<xsl:text>
</xsl:text>
<xsl:value-of select="$picname"/>
[![<xsl:value-of select="$picname"/>](<xsl:value-of select="$piclink"/>)](<xsl:value-of select="$doclink"/>)
    </xsl:function>

	<xsl:function name="zenta:writeTestcasesAsText">
		<xsl:param name="behaviours" />
		<xsl:for-each select="$behaviours//task">
----------------------------------------------------------------------------
Behaviour: <xsl:value-of select="concat(@service, '/', @behaviour)" />
Milestone: <xsl:value-of select="milestone/@name"/>

Behaviour description:

<xsl:copy-of select="behaviour/documentation/text()"/>
Annotations for the test cases:

    @TestedBehaviour("<xsl:value-of select="@behaviour" />")
    @TestedService("<xsl:value-of select="@service" />")

The production code is at <xsl:value-of select="concat(@package, '.', @service)" />.java
The testbase is at <xsl:value-of select="concat(@package, '.', @service)" />TestBase.java
The testcase is at <xsl:value-of select="concat(@package, '.', @service)" /><xsl:value-of select="zenta:camelCase(@behaviour)"/>Test.java
<xsl:variable name="service" select="service"/>

<xsl:variable name="serviceview" select="$zenta//element[@id=$service/@id]/../*[child]/@id"/>
The service:
                <xsl:copy-of select="zenta:drawpic($serviceview)"/>

How to work:
* identify what of the above is relevant to your service
* come up with a test about the relevant part
  * create a new empty test
  * make sure that the test description is understandable for the end user
  * put it to @DisplayName annotation for the test
  * write the test according to the test description
  * see the test fail in the right way
  * write the production code needed to pass the test, and no more
  * see the test pass
  * get rid of any PMD violations, comments and other problems in the code
  * see that all tests pass
* repeat until you covered all the relevant parts of the behaviour
* submit your pull request against the milestone of the task

If you have questions, see the [FAQ](https://kodekonveyor.com/coder-faq/), or ask on [our discord channel](https://discord.gg/CkW6bvp) or ask your mentor.

		</xsl:for-each>
	</xsl:function>

	<xsl:template name="behaviours">
		<xsl:variable name="root" select="/" />
		<xsl:for-each select="//element[@template='false' and @xsi:type='Process Step']">
	            <xsl:variable name="step" select="."/>
	            <xsl:variable name="immediatePackage" select="zenta:neighbours($root,$step,'contains,2')[@xsi:type='Package']"/>
		    <xsl:variable name="service" select="zenta:neighbours(/,$step,'is implemented by/implements,1')"/>
		    <xsl:if test="$immediatePackage and $service">
		            <xsl:for-each select="zenta:neighbours(/,.,'contains,1')">
		            	<xsl:variable name="behaviour" select="."/>
		                <task>
				    <xsl:attribute name="service" select="$service/@name"/>
				    <xsl:attribute name="step" select="$step/@name"/>
		                    <xsl:attribute name="behaviour" select="$behaviour/@name"/>
		                    <xsl:attribute name="package" select="zenta:fullpackage($step)"/>
		                    <xsl:variable name="implementedBehaviour" select="$implemented//behaviour[@name = $behaviour/@name and ../@service=$service/@name]"/>
		                    <xsl:variable name="issue" select="$issues//issue[summary=concat($service/@name,'/',$behaviour/@name)]"/>
		                    <xsl:attribute name="issueUrl" select="$issue/@url"/>
		                    <xsl:attribute name="status" select="
		                        if($issue/@status = 'open')
		                        then
		                            if(not($implementedBehaviour/testcase))
		                            then
		                                'open'
		                            else
		                                'should-be-closed'
		                        else
		                            if(not($implementedBehaviour/testcase))
		                            then
		                                'should-make-issue'
		                            else
		                                'ready'
	                    "/>
		    		    <xsl:for-each select="zenta:neighbours($root,$step,'implements,2')">
					    <milestone>
						    <xsl:copy-of select="@name"/>
					    </milestone>
				    </xsl:for-each>
		                    <implemented>
		                        <xsl:copy-of select="$implementedBehaviour/testcase"/>
		                    </implemented>
		                    <xsl:copy-of select="$issue"/>
				    <service><xsl:copy-of select="$service/(@name|@id|documentation)"/></service>
				    <behaviour><xsl:copy-of select="$behaviour/(@name|@id|documentation)"/></behaviour>
		                </task>
		            </xsl:for-each>
		    </xsl:if>
		</xsl:for-each>
	</xsl:template>

</xsl:stylesheet>

