use Developer Command Promt for VS
 - Create root certificate
   makecert -pe -n "CN=RootCertificate" -ss root -sr LocalMachine -sky signature -r "RootCertificate.cer"
 - Create another certificate for SSL, and use root certificate to authorize this new certificate
   makecert -pe -n "CN=SSLCertificate" -ss my -sr LocalMachine -sky exchange -eku 1.3.6.1.5.5.7.3.3 -in "RootCertificate" -is root -ir LocalMachine -sp "Microsoft RSA SChannel Cryptographic Provider" -sy 12 SSLCertificate.cer

 - register port with SSL certificate
   netsh http show sslcert
   netsh http delete sslcert ipport=0.0.0.0:8002
   netsh http add sslcert ipport=0.0.0.0:8002 certhash=d2e28d7201670769109c606538db009c7b15e9f1 appid={11223344-4455-6677-8899-AABBCCDDEEFF} 

