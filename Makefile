export MODEL_BASENAME=taskmarket
export REPO_NAME=TaskMarket
export GITHUB_ORGANIZATION=kode-konveyor
LANGUAGE=java

include /usr/local/toolchain/rules.java

all: install shippable/behaviours.xml pmdcheck coveragecheck

shippable/behaviours.xml: $(MODEL_BASENAME).rich
	zenta-xslt-runner -xsl:xslt/generate_behaviours.xslt -s $(MODEL_BASENAME).rich modelbasename=$(MODEL_BASENAME) reponame=$(REPO_NAME) github_org=$(GITHUB_ORGANIZATION)

shippable/$(MODEL_BASENAME)-implementedBehaviours.xml shippable/$(MODEL_BASENAME)-implementedBehaviours.docbook: buildreports shippable $(MODEL_BASENAME).rich
	zenta-xslt-runner -xsl:xslt/generate_implemented_behaviours.xslt -s target/test/javadoc.xml modelbasename=$(MODEL_BASENAME)

sonar:
	echo "not using sonar"

cpdcheck: javadoc
	if grep -A 1 "<duplication" target/pmd.xml; then exit 1; fi

pmdcheck: javadoc
	if grep -A 1 "<violation" target/pmd.xml; then exit 1; fi

clean: $(BEFORE_CLEAN)
	if git clean -ndx|grep "^Would remove src"; then echo  "\n\n----------  WARNING! ---------------\nnot deleting anything: please remove or add the above by hand\n\n";exit 1; fi
	git clean -fdx

