# gRPC

1. gRPC는 HTTP/2 기반이다.
1. 웹 애플리케이션에서 gRPC를 쓰려면 웹 애플리케이션도 HTTP/2로 써야 좋다.
1. HTTP/2를 쓰려면 SSL 인증서가 필요하다.

## SSL 인증서

[Let's Encrypt](https://letsencrypt.org)로 인증서를 발급한다.
  - `git clone https://github.com/letsencrypt/letsencrypt`는 Homebrew가 root 권한으로 사용할 수 없기 때문에 쓸 수 없다.

## Let's Encrypt

설치 :
```
➜  grpc git:(example/grpc) ✗ brew install certbot
```

발급 : 로컬에서는 실패한다.
```
➜  grpc git:(example/grpc) ✗ sudo certbot certonly
Password:
Sorry, try again.
Password:
Saving debug log to /var/log/letsencrypt/letsencrypt.log

How would you like to authenticate with the ACME CA?
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
1: Apache Web Server plugin (apache)
2: Spin up a temporary webserver (standalone)
3: Place files in webroot directory (webroot)
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Select the appropriate number [1-3] then [enter] (press 'c' to cancel): 2
Plugins selected: Authenticator standalone, Installer None
Enter email address (used for urgent renewal and security notices) (Enter 'c' to
cancel): just.burrow@lul.kr

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Please read the Terms of Service at
https://letsencrypt.org/documents/LE-SA-v1.2-November-15-2017.pdf. You must
agree in order to register with the ACME server at
https://acme-v02.api.letsencrypt.org/directory
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
(A)gree/(C)ancel: A

- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
Would you be willing to share your email address with the Electronic Frontier
Foundation, a founding partner of the Let's Encrypt project and the non-profit
organization that develops Certbot? We'd like to send you email about our work
encrypting the web, EFF news, campaigns, and ways to support digital freedom.
- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
(Y)es/(N)o: N
Please enter in your domain name(s) (comma and/or space separated)  (Enter 'c'
to cancel): example.lul.kr
Obtaining a new certificate
Performing the following challenges:
http-01 challenge for example.lul.kr
Waiting for verification...
Challenge failed for domain example.lul.kr
http-01 challenge for example.lul.kr
Cleaning up challenges
Some challenges have failed.

IMPORTANT NOTES:
 - The following errors were reported by the server:

   Domain: example.lul.kr
   Type:   connection
   Detail: dns :: DNS problem: NXDOMAIN looking up A for
   example.lul.kr

   To fix these errors, please make sure that your domain name was
   entered correctly and the DNS A/AAAA record(s) for that domain
   contain(s) the right IP address. Additionally, please check that
   your computer has a publicly routable IP address and that no
   firewalls are preventing the server from communicating with the
   client. If you're using the webroot plugin, you should also verify
   that you are serving files from the webroot path you provided.
 - Your account credentials have been saved in your Certbot
   configuration directory at /etc/letsencrypt. You should make a
   secure backup of this folder now. This configuration directory will
   also contain certificates and private keys obtained by Certbot so
   making regular backups of this folder is ideal.
```

도메인 연결한 서버가 필요할 것 같으니 HTTP/2 + HTTPS + gRPC 적용은 보류.

## 참고

- [Certbot](https://certbot.eff.org/)
- [Let’s Encrypt 인증서 발급 방법](https://hiseon.me/server/letsencrypt-ssl-certificate/)
- [Spring Boot Secured By Let's Encrypt](https://dzone.com/articles/spring-boot-secured-by-lets-encrypt)
