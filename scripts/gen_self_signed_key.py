#!/usr/bin/python3

'''
This script will generate a self signed key in a java keystore to be used
by apache tomcat for SSL support

Notes:
- Please note that for tomcat to work the store password MUST be equal to
the keypass. See that in the code below.
- You can see whats in the created file like this:
keytool -list -v
- to list all keys in the cacerts file use:
keytool -list -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit

References:
https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html
'''

###########
# imports #
###########
import os  # for environ, unlink
import os.path  # for isfile, expanduser, join
import subprocess  # for check_call

###########
# globals #
###########
# password for the key store
OPT_STOREPASS='PR0rV7320u'
# password for the key
OPT_KEYPASS=OPT_STOREPASS
# alias for the key
OPT_ALIAS='tomcat'
# keystore file to use
OPT_KEYSTORE=os.path.join(os.path.expanduser('~'), '.keystore')
# the algorithm for the key? (RSA, DSA, DES)
OPT_KEYALG='RSA'
# for how long will the key be valid?
OPT_VALIDITY=str(360*10)
# how big is the key?
OPT_KEYSIZE='2048'
# add the key to the jdk keystore?
OPT_ADD_TO_CACERTS=True
# password for the JDK cacerts file
OPT_CACERTS_PASS='changeit'
# cer file to use
OPT_CER=os.path.join(os.path.expanduser('~'), '.keystore.tomcat.cer')
# cacerts file to add to
OPT_CACERTS=os.path.join(os.environ['JAVA_HOME'], 'jre/lib/security/cacerts')
'''
Here is the reference from keytool(1):
    CN=commonName
    OU=organizationUnit
    O=organizationName
    L=localityName
    S=stateName
    C=country
CN=Mark Smith, OU=Java, O=Oracle, L=Cupertino, S=California, C=US
'''
OPT_DATA={
    # this is the name of the host for which you want the certificate
    # it must be identical to the name that you will access (e.g. https://localhost:8443/...).
    'cn': 'localhost',
    'ou': 'WebApps',
    'o': 'Meta',
    'l': 'Gush-Dan',
    's': 'None',
    'c': 'IL',
}

########
# code #
########
# remove the old file
if os.path.isfile(OPT_KEYSTORE):
    os.unlink(OPT_KEYSTORE)
# set the environment variable STOREPASS to have the right password
os.environ['STOREPASS']=OPT_STOREPASS
# call keytool to generate the keystore
subprocess.check_call([
    'keytool',
    '-genkey',
    '-alias',
    OPT_ALIAS,
    '-keyalg',
    OPT_KEYALG,
    '-validity',
    OPT_VALIDITY,
    '-keysize',
    OPT_KEYSIZE,
    '-storepass:env',
    'STOREPASS',
    '-dname',
    'cn={cn}, ou={ou}, o={o}, l={l}, s={s}, c={c}'.format(**OPT_DATA),
    '-keypass',
    OPT_KEYPASS,
    '-keystore',
    OPT_KEYSTORE,
])
# print a message that all is ok
print(f'created keystore file [{OPT_KEYSTORE}]...')
# export our certificate to a .cer file
subprocess.check_call(
    [
        'keytool',
        '-export',
        '-alias',
        OPT_ALIAS,
        '-storepass:env',
        'STOREPASS',
        '-file',
        OPT_CER,
    ],
    stderr=subprocess.DEVNULL, # because keytool is a little noisy
)
print(f'exported the tomcat certificate to [{OPT_CER}]...')

if OPT_ADD_TO_CACERTS:
    # delete the old key (may not succeed if this is the first time)
    subprocess.call(
        [
            'keytool',
            '-delete',
            '-alias',
            OPT_ALIAS,
            '-keystore',
            OPT_CACERTS,
            '-storepass',
            OPT_CACERTS_PASS,
        ]
    )
    subprocess.check_call(
        [
            'keytool',
            '-importcert',
            '-noprompt',
            '-keystore',
            OPT_CACERTS,
            '-storepass',
            OPT_CACERTS_PASS,
            '-alias',
            OPT_ALIAS,
            '-file',
            OPT_CER,
        ],
        stderr=subprocess.DEVNULL, # because keytool is a little noisy
    )
    print(f'imported the tomcat certificate to [{OPT_CACERTS}]...')
