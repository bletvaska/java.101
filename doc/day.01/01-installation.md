# Inštalácia

Budeme potrebovať nainštalovať nasledovný softvér:

* Java JDK/JRE
* IntelliJ IDEA Community
* Git


## Windows OS

* najprv nainštalujeme správcu balíčkov [Chocolatey](https://chocolatey.org) podľa [týchto pokynov](https://chocolatey.org/install)

    * spustiť `Power Shell` s právami superpoužívateľa
    * spustiť inštaláciu príkazom:

        ```bash
        $ Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
        ```

    * overiť inštaláciu príkazom:

        ```bash
        $ choco -?
        ```

* pomocou správcu balíčkov `Chocolatey` nainštalovať potrebné balíčky

    * spustiť `Power Shell` s právami superpoužívateľa
    * spustiť inštaláciu príkazom:

        ```bash
        $ choco install -y cmder intellijidea-community temurin temurinjre gradle
        ```

* otestovať inštaláciu príkazmi:

    ```bash
    $ java -version
    $ javac -version
    ```

## Linux OS

* nainštalovať JDK aj JRE [Eclipse Temurin](https://adoptium.net/installation/#linux-pkg)

* aktualizovať alternatívy:

    ```bash
    $ sudo update-alternatives --config java
    ```

* otestovať inštaláciu príkazmi:

    ```bash
    $ java -version
    openjdk version "20.0.1" 2023-04-18
    OpenJDK Runtime Environment Temurin-20.0.1+9 (build 20.0.1+9)
    OpenJDK 64-Bit Server VM Temurin-20.0.1+9 (build 20.0.1+9, mixed mode, sharing)
    ```

    ```bash
    $ javac -version
    javac 20.0.1
    ```

