export MODEL_BASENAME=taskmarket
export REPO_NAME=TaskMarket
export GITHUB_ORGANIZATION=kode-konveyor
export CONSISTENCY_INPUTS=taskmarket.rich shippable/behaviours.xml
LANGUAGE=java
BEFORE_ALL=runapache

include /usr/local/toolchain/rules.java

runapache:
	tools/runApache
	touch runapache
