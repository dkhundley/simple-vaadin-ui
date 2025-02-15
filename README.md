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
TBA

### Windows Installation Steps
TBA

### GitHub Codespaces Installation Steps
TBA


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