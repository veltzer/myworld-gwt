# shellcheck shell=bash
#source this file!!!

# java stuff
export JAVA_VERSION=1.8.0_92
JAVA_HOME="$(path_abs "${HOME}"/install/jdk${JAVA_VERSION})"
export JAVA_HOME
PATH="$(path_prefix "${JAVA_HOME}/bin" "${PATH}")"
export PATH
MANPATH="$(path_prefix "${JAVA_HOME}/man" "${MANPATH}")"
export MANPATH

# maven stuff
export MVN_VERSION=3.3.9 # my own version
M2_HOME="$(path_abs "${HOME}"/install/apache-maven-${MVN_VERSION})"
export M2_HOME
PATH="$(path_prefix "${M2_HOME}/bin" "${PATH}")"
export PATH

# ant stuff
export ANT_VERSION=1.9.7
ANT_HOME="$(path_abs "${HOME}"/install/apache-ant-${ANT_VERSION})"
export ANT_HOME
PATH="$(path_prefix "${ANT_HOME}/bin" "${PATH}")"
export PATH
# This is needed so that secure connections to apache tomcat will succeed
# We are no longer using this instead we are importing our certificate to the cacerts
# store of java directly
# (the old ANT_OPTS trustStore line with an inline password was removed)

# ivy stuff
export IVY_VERSION=2.4.0
IVY_HOME="$(path_abs "${HOME}"/install/apache-ivy-${IVY_VERSION})"
export IVY_HOME

# gwt stuff
export GWT_VERSION=2.7.0
GWT_HOME="$(path_abs "${HOME}"/install/gwt-${GWT_VERSION})"
export GWT_HOME

# gxt stuff
export GXT_VERSION=4.0.0-gpl
GXT_HOME="$(path_abs "${HOME}"/install/gxt-${GXT_VERSION})"
export GXT_HOME

# tomcat stuff
export TOMCAT_VERSION=8.0.35
TOMCAT_HOME="$(path_abs "${HOME}"/install/apache-tomcat-${TOMCAT_VERSION})"
export TOMCAT_HOME
PATH="$(path_prefix "${TOMCAT_HOME}/bin" "${PATH}")"
export PATH
