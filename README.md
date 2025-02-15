![](assets/vaadin-banner.png)

# Building a Simple Java-Based UI with Vaadin
In this repository, I will share how you can build a simple Java-based UI with **Vaadin**. Vaadin is an open source framework that allows users to build a simple web-based user interface using their drag-and-drop builder.

To keep things easy and simple to understand, we're specifically going to be building a **pizza maker** (or, I guess, pizza order taker, if we're being specific). We're going to keep it simple by building only two screens, or *views*, which include:

1. A form for the user to build their pizza, including the size, crust, and toppings.
2. A confirmation screen that shows the user what they've ordered along with subtotal, tax, tip, and final total.

## Prerequisites
There are two core things that we're going to be doing across this tutorial:

1. Building the Vaadin UI via Vaadin's drag-and-drop builder.
2. Altering the code we'll get from the drag-and-drop builder to make it work for our pizza maker.

The drag-and-drop builder is simply accessed by visiting Vaadin's website, so we don't need anything particularly special here. (Note: As an iPad user, I did find that Vaadin's builder didn't like Safari for iPad, but it worked just fine with Opera on the iPad. Go figure.)

For the code, this is where things get a bit trickier. I have confirmed that this code will work on both **macOS** and **Windows**, but I'm not able to confirm anything about Chromebooks. I'll provide installation instructions for both macOS and Windows, but for the sake of simplicity, I'm actually going to recommend **GitHub Codespaces**.

### What is GitHub Codespaces?
GitHub Codespaces is a cloud-based development environment that allows you to code directly from your browser. In order to use GitHub Codespaces, all you need to do is to sign up for a free GitHub account. Now, there is a quota limitation on how much you can use GitHub Codespaces each month, but I've found the free tier is pretty generous. If you'd like to learn additional details about GitHub Codespaces, you can visit their [official website](https://github.com/features/codespaces).

Here are the reasons I like GitHub Codespaces:

- **Minimal fussing around with installations**: While I will provide instructions for directly using this code on macOS or Windows, you'll quickly see that getting your computer's setup right can be a pain in the neck. GitHub Codespaces allows you to designate a sort of "blueprint" for your development environment in the form of a `devcontainer.json` file. Actually, you'll see that in the `.devcontainer` folder of this repository, I've already created one of these "blueprint" files that is designed exactly for our Java / Vaadin work here.
- **Usable from any device with a web browser**: I personally like to use my iPad as my primary computer, but unfortunately, an iPad's operating system is not at all conducive for coding. GitHub Codespaces allows me to code from my iPad, which is a huge win for me. Additionally, I know a lot of students use Chromebooks, so as long as the school doesn't have this blocked, this is an excellent way for students to also code in a legit dev environment on their Chromebooks.
- **Fully fledged VS Code**: GitHub Codespaces is basically VS Code in a browser, and it's not a watered-down version of VS Code. You can install extensions, use the terminal, and do everything you'd expect to do in a normal VS Code environment. This also includes the allowance of GitHub Copilot (if you have the subscription for that).

Again, this tutorial will cover how to use Vaadin on macOS and Windows, but I highly recommend using GitHub Codespaces for students. It just makes things much simpler since installation can be one of the trickiest parts of coding. (And transparently, I struggled a lot with getting all the installations right on my Mac and Windows machines.)

## Installation Instructions
In this section, I'll demonstrate three separate ways to get this code up and running on your computer, depending on the kind of computer you have. These three ways include:

- **macOS**: This is the operating system that Apple computers run on.
- **Windows**: This is the operating system that most PCs run on.
- **GitHub Codespaces**: This is a cloud-based development environment that allows you to code directly from your browser that we discussed briefly in the "Prerequisites" section.

Regardless of which computer type you use, we'll have to also install some additional tools to help us with our Java development. These tools include:

- **Java 21 LTS**: This is the version of Java that we'll be using for this tutorial. LTS stands for "long-term support," and it's generally a good idea to go with the LTS version. At the time I'm writing this, the LTS version is Java 21 LTS.
- **Maven**: Think of this as a tool that helps us manage all the other tools and depndencies we'll need for our Java project. It seeks to make a developer's life easier by handling all the "heavy lifting" of managing dependencies.
- **Spring Boot**: This is a framework that makes it easy to create stand-alone, production-grade Spring-based Applications that you can "just run." It's a bit more complex than that, but that's the general idea. In our case, Vaadin uses Spring Boot under the hood, so we'll need to have it installed to make this code work properly.

### macOS Installation Steps
A common way that software developers use to manage software installations on macOS is through a package manager called **Homebrew**. Homebrew is a free and open-source software package management system that simplifies the installation of software on Apple's macOS operating system. The steps below assume that you already have Homebrew installed on your Mac. If you don't have Homebrew installed, you can find installation instructions on the [Homebrew website](https://brew.sh/). It's pretty easy to get going with Homebrew, so the linked tutorial will guide you there just fine.

After you have Homebrew installed, you can follow these steps to install the additional tools we need:

1. Open "Terminal" on your Mac. You can find this by searching for "Terminal" in Spotlight (Cmd + Space).
2. Install Java 21 LTS by running the following command:
    ```bash
    brew install openjdk@21
    ```
3. Install Maven by running the following command:
    ```bash
    brew install maven
    ```
4. Install Spring Boot by running the following command:
    ```bash
    brew install spring-boot
    ```
5. You can verify that everything is installed correctly by running the following commands:
    ```bash
    java -version
    mvn -version
    spring --version
    ```

If you see the version numbers for Java, Maven, and Spring Boot, respectively, then you're good to go! 

### Windows Installation Steps
For Windows, we'll be using **Scoop** as our package manager. Scoop is similar to Homebrew for macOS and makes installing developer tools much easier. Here's how to get everything set up:

1. First, open PowerShell as Administrator (right-click and select "Run as Administrator")

2. Install Scoop by running these commands:
    ```powershell
    Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
    irm get.scoop.sh | iex
    ```

3. Install Java 21 LTS:
    ```powershell
    scoop bucket add java
    scoop install openjdk21
    ```

4. Install Maven:
    ```powershell
    scoop install maven
    ```

5. Install Spring Boot:
    ```powershell
    scoop bucket add extras
    scoop install springboot
    ```

6. Verify the installations by running:
    ```powershell
    java -version
    mvn -version
    spring --version
    ```

If you see version numbers displayed for each command, you're all set!

### GitHub Codespaces Installation Steps
As I shared before, this is the path that I recommend for most people given that it's the only path that I can guarantee will work for everybody. This is because I can't verify what specific version of macOS or Windows you're on, making it hard to give specific recommendations if you run into any issues. With GitHub Codespaces, you're using a standardized environment that I know will work for this tutorial.

In order to make use of GitHub Codespaces, you will also need to create a **GitHub repository**. We will cover the specific steps for how to do this down below, but it's important to share what a GitHub repository is. A GitHub repository is a place where you can store and manage your code. It's like a folder for your project that lives on the internet. You can think of it as a cloud-based storage space for your code that you can access from anywhere. It might seem like an extra unnecessary step to create a GitHub repository to be able to use a GitHub Codespace, but it actually works out very well because this will allow you to easily save your work back to GitHub and be able to share it with others, including your fellow students and teacher.

Here are the steps to get set up with GitHub Codespaces:

1. Sign up for a free GitHub account if you don't already have one. You can do this by visiting the [GitHub website](https://github.com/) and following the sign up process behind the "Sign Up" button.


### Visual Studio Code
In addition to installing all the software we've done so far, we will also need to ensure that we have installed **Visual Studio Code**, or **VS Code** for short. To be clear, when it comes to Java development, VS Code is actually NOT the number one recommended integrated development environment (IDE). That honor goes to **IntelliJ IDEA**. However, I'm recommending VS Code for this tutorial for a number of reasons. In short, VS Code is the most popular IDE in the world for software developers, and it's also the IDE that GitHub Codespaces uses. So, if you're following along with this tutorial, you'll need to have VS Code installed on your computer.

To install VS Code on macOS or Windows, all you have to do is visit the [official VS Code website](https://code.visualstudio.com/) and download the installer for your operating system. The installation process is pretty straightforward, and you should be up and running in no time. If you're using GitHub Codespaces, you don't need to worry about this step, as VS Code is already installed for you.


## Part 1: Creating the Vaadin UI
This section will largely take place in your web browser. We're going to be using Vaadin's drag-and-drop builder to create the UI for our pizza maker. If you've ever used a website builder like Wix or Squarespace, this is going to feel very similar. 

### The Vaadin Builder
Here are the steps for navigating to Vaadin's builder:

1. Go to Vaadin's website: [https://start.vaadin.com/](https://start.vaadin.com/)
2. Click on the blue "Start a Project" button.
3. Give your app a name and select "None" for the project layout. (It doesn't necessarily matter which layout you choose, but "None" is the simplest and the one I used for this tutorial.)

From here, the UI is pretty straightforward: You drag stuff from the left nagivation onto the working area on the right. You'll notice in the left navigation also that there is an ability to create additional views. As a reminder, we're only going to be creating two views in this tutorial: the pizza builder and the confirmation screen. If you're following along with me, you'll want to ensure you build the two appropriate views.

Now, I really like Vaadin's builder, but there are a couple "gotchas" we need to address:

- **Saving your project on Vaadin**: Let's say that you start building a UI on Vaadin's builder but then need to take a break for whatever reason. Unless you are a logged in, authenticated user, *Vaadin will not save your work!* Fortunately, you can sign up simply for a free account, and in my experience, I've had no issues with spam or anything like that.
- **Limited ability to directly alter text**: Something I found slightly inconsistent with Vaadin's builder is which text elements you could directly alter. For example, if you want to add an H1 header, you can directly change that text in Vaadin's builder. On the flip side, if you try to add a "toggle detail" component, it will only show some canned text that you can't directly alter in the Vaadin builder. This isn't a showstopper given that we can still alter all code to our liking after we export it from Vaadin's builder, but it's something to be aware of.
- **Zero ability to add "behavior" / functions**: This is not a knock at all on Vaadin's builder but more a reminder: the Vaadin UI builder is purely, solely for building the look and feel of the UI. The Vaadin builder then will allow us to export this as Java code, and then we can make those alterations on our own. (Which is all about part 2 of our tutorial!) But if you're expecting something like the ability to add 2 numbers together in the Vaadin builder, the Vaadin builder is not going to be able to do that for you.


### Exporting the Java Code from the Vaadin Builder
Once you're happy with your UI, we can export the code by following these steps:

1. In your open project in the Vaadin builder, click the blue "Download Project" button.
2. You will be greeted with a form with a number of fields. Here's how I'd recommend filling them out:
    - Artifact ID: This is the name of your project. I'd recommend keeping it simple and all lowercase. For example, I named my project `pizzamaker`.
    - Group ID: This is the package name for your project. I'd recommend keeping it simple and all lowercase. For example, I named my package `com.dkhundley.pizzamaker`.
    - Platform Version: At the time I'm writing this, the recommended version is Vaadin 24.6.
    - Java Version: You might see some options that note LTS. LTS means "long-term support," and it's generally a good idea to go with the LTS version. At the time I'm writing this, the LTS version is Java 21 LTS.
    - Main Layout: "Java (Flow)"
    - Frontend Build Tool: "npm"
    - Database: "h2"
    - Deselect / uncheck all the other options.
3. Finish things up by clicking the "Download" button.

What this will do is download a `.zip` file to your computer. Unzip this file, and you'll see a number of files and folders. This represents the Java code that Vaadin has generated for you based on your UI in the Vaadin builder. In this repository, you might have noticed that there's a `pizza-maker` folder and a `pizza-maker-raw` folder. The "raw" version is version that Vaadin generated for me, completely unaltered.

This effectively ends part 1 of our tutorial! In part 2, you'll see the alterations we'll need to make in order to make our pizza maker work as intended. You'll also be able to directly contrast how the code will differ in the end from the "raw" code that Vaadin provided us in part 1 here.

## Part 2: Altering the Java Code
Now that we've completed building the look-and-feel of our app using Vaadin's builder, we're ready to make alterations to it so that we can get it fully functioning as we'd expect. If you have not done so already, please ensure you have completed the steps in the "Installation Instructions" section above.