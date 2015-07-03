#!/usr/bin/python3

'''
This script will generate a self signed key in a java keystore to be used
by apache tomcat for SSL support

Notes:
- Please note that for tomcat to work the store password MUST be equal to
the keypass. See that in the code below.
- You can see whats in the created file like this:
keytool -list -v

References:
https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html

	Mark Veltzer <mark@veltzer.net>
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
	'cn': 'Mark Veltzer',
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
