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
import subprocess # for check_call
import os # for environ, unlink
import os.path # for isfile, expanduser, join

###########
# globals #
###########
# password for the key store
opt_storepass='PR0rV7320u'
# password for the key
opt_keypass=opt_storepass
# alias for the key
opt_alias='tomcat'
# keystore file to use
opt_keystore=os.path.join(os.path.expanduser('~'), '.keystore')
# the algorithm for the key? (RSA, DSA, DES)
opt_keyalg='RSA'
# for how long will the key be valid?
opt_validity=str(360*10)
# how big is the key?
opt_keysize='2048'
# add the key to the jdk keystore?
opt_add_to_cacerts=True
# password for the JDK cacerts file
opt_cacerts_pass='changeit'
# cer file to use
opt_cer=os.path.join(os.path.expanduser('~'), '.keystore.tomcat.cer')
# cacerts file to add to
opt_cacerts=os.path.join(os.environ['JAVA_HOME'], 'jre/lib/security/cacerts')
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
opt_data={
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
if os.path.isfile(opt_keystore):
    os.unlink(opt_keystore)
# set the environment variable STOREPASS to have the right password
os.environ['STOREPASS']=opt_storepass
# call keytool to generate the keystore
subprocess.check_call([
    'keytool',
    '-genkey',
    '-alias',
    opt_alias,
    '-keyalg',
    opt_keyalg,
    '-validity',
    opt_validity,
    '-keysize',
    opt_keysize,
    '-storepass:env',
    'STOREPASS',
    '-dname',
    'cn={cn}, ou={ou}, o={o}, l={l}, s={s}, c={c}'.format(**opt_data),
    '-keypass',
    opt_keypass,
    '-keystore',
    opt_keystore,
])
# print a message that all is ok
print('created keystore file [{0}]...'.format(opt_keystore))
# export our certificate to a .cer file
subprocess.check_call(
    [
        'keytool',
        '-export',
        '-alias',
        opt_alias,
        '-storepass:env',
        'STOREPASS',
        '-file',
        opt_cer,
    ],
    stderr=subprocess.DEVNULL, # because keytool is a little noisy
)
print('exported the tomcat certificate to [{0}]...'.format(opt_cer))

if opt_add_to_cacerts:
    # delete the old key (may not succeed if this is the first time)
    subprocess.call(
        [
            'keytool',
            '-delete',
            '-alias',
            opt_alias,
            '-keystore',
            opt_cacerts,
            '-storepass',
            opt_cacerts_pass,
        ]
    )
    subprocess.check_call(
        [
            'keytool',
            '-importcert',
            '-noprompt',
            '-keystore',
            opt_cacerts,
            '-storepass',
            opt_cacerts_pass,
            '-alias',
            opt_alias,
            '-file',
            opt_cer,
        ],
        stderr=subprocess.DEVNULL, # because keytool is a little noisy
    )
    print('imported the tomcat certificate to [{0}]...'.format(opt_cacerts))
