# TOFIX

Findings from a code scan on 2026-10-04.

## High

- `src/META-INF/persistence.xml:21` - a real-looking MySQL password (`fVgyzpYy5N`, user `mark`) is committed and is packaged into the war as a resource (`pom.xml` copies everything under `src/` except `*.java`); remove it from the file and history, rotate it, and read the credentials at run time (JNDI datasource or a file outside the repo; the secret itself belongs in pass(1)). `doc/TODO.txt:41` already lists this.
- `scripts/gen_self_signed_key.py:30` - the keystore/key password `PR0rV7320u` is hardcoded, and it is also passed on the command line (`-keypass`, line 96) where it is visible in `ps`; fetch it from pass(1) at run time and hand it to keytool via `-keypass:env`/`-storepass:env` only.

## Medium

- `src/org/meta/gwtworld/server/DataServiceImpl.java:31` - every RPC call creates a new `EntityManagerFactory` and `EntityManager` (also line 43) and never closes either, leaking a connection pool per request; create the factory once (servlet `init`) and close each `EntityManager` in a `finally`/try-with-resources.
- `src/org/meta/gwtworld/server/DataServiceImpl.java:63` - `assert(l.size()==1)` is a no-op in a servlet container (assertions are off by default), so a missing default row fails at `l.get(0)` with `IndexOutOfBoundsException` and a duplicate is silently accepted; check the size explicitly and throw a meaningful exception.
- `scripts/source_me.sh:37` - sets up GWT 2.7.0 and GXT 4.0.0-gpl (line 42), while `pom.xml:19` builds against GWT 2.6.1 and `pom.xml:43` GXT 3.1.1; it also sets up ant/ivy (lines 20-34) which the maven build no longer uses, and calls `path_abs`/`path_prefix` that are not defined anywhere in this repo. Update it to the maven toolchain or delete it.
- `scripts/gen_self_signed_key.py:50` - `os.environ['JAVA_HOME']` raises `KeyError` when unset, and `jre/lib/security/cacerts` only exists on Java 8; on Java 9+ the file is `$JAVA_HOME/lib/security/cacerts` (or use `keytool -cacerts`). Handle both.
- `war/WEB-INF/appengine-web.xml:19` - points `java.util.logging.config.file` at `WEB-INF/logging.properties`, which does not exist in `war/WEB-INF/`; add the file or drop the property. The schemaLocation at line 3 (kenai.com) is also a dead host.

## Low

- `src/org/meta/gwtworld/client/Gwtworld.java:174` - the Save button (created and validated at lines 138-166) is never added to the panel (`//panel.add(save);`), so the form cannot be submitted; add it and wire a save RPC, or remove the dead button logic.
- `xsd/appengine-web.xsd:85` - a second, diverged copy of `war/WEB-INF/appengine-web.xsd` (the war copy has extra `env`/`api-config` elements) that nothing references; delete one copy.
- `pom.xml:102` - the forked GWT JVM path `/usr/lib/jvm/java-8-openjdk-amd64/bin/java` is amd64-only; derive it from a property (e.g. `${env.JAVA8_HOME}`) so the build works on other architectures.
- `doc/TODO.txt:34` - build-system items refer to ant and `build.xml` (also line 40), which no longer exist since the move to maven; prune the stale entries.
- `doc/links.txt:4` - `mojo.codehaus.org` (Codehaus shut down in 2015) and `developers.google.com/web-toolkit` (line 8) are dead; point to `gwt-maven-plugin` (tbroyer) docs and `gwtproject.org`.
- `doc/deploy_the_app.txt:1` - ends after the first sentence, while `README.md:10` advertises it as Tomcat deployment notes; complete it or drop the README claim.
