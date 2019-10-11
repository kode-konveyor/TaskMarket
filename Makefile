export MODEL_BASENAME=taskmarket
export REPO_NAME=TaskMarket
export GITHUB_ORGANIZATION=kode-konveyor
export SONAR_ORG=$(GITHUB_ORGANIZATION)

include /usr/local/toolchain/rules.java

#temporary workaround, waiting for toolchain 0.1.2
shippable/$(MODEL_BASENAME)-implementedBehaviours.xml: buildreports shippable $(MODEL_BASENAME).rich
	zenta-xslt-runner -xsl:xslt/generate-behaviours.xslt -s target/test/javadoc.xml outputbase=shippable/$(MODEL_BASENAME)- modelbasename=$(MODEL_BASENAME)

