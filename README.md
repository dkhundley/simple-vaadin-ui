![](assets/vaadin-banner.png)

# Building a Simple Java-Based with Vaadin
In this repository, I will share how you can build a simple Java-based UI with **Vaadin**. Vaadin is an open source framework that allows users to build a simple web-based user interface using their drag-and-drop builder.

To keep things easy and simple to understand, we're specifically going to be building a **pizza maker** (or, I guess, pizza order taker, if we're being specific). We're going to keep it simple by building only two screens, or *views*, which include:

1. A form for the user to build their pizza, including the size, crust, and toppings.
2. A confirmation screen that shows the user what they've ordered along with subtotal, tax, tip, and final total.

## Prerequisites
There are two core things that we're going to be doing across this tutorial:

1. Building the Vaadin UI via Vaadin's drag-and-drop builder.
2. Altering the code we'll get from the drag-and-drop builder to make it work for our pizza maker.

The drag-and-drop builder is simply accessed by visiting Vaadin's website, so we don't need anything particularly special here. (Note: As an iPad user, I did find that Vaadin's builder didn't like Safari for iPad, but it worked just fine with Opera on the iPad. Go figure.)

For the code, this is where things get a bit trickier. I have confirmed that this code will work on both macOS and Windows, but I'm not able to confirm anything about Chromebooks. I'll provide installation instructions for both macOS and Windows, but for the sake of simplicity, I'm actually going to recommend **GitHub Codespaces**.

### What is GitHub Codespaces?
GitHub Codespaces is a cloud-based development environment that allows you to code directly from your browser. In order to use GitHub Codespaces, all you need to do is to sign up for a free GitHub account. Now, there is a quota limitation on how much you can use GitHub Codespaces each month, but I've found the free tier is pretty generous. If you'd like to learn additional details about GitHub Codespaces, you can visit their [official website](https://github.com/features/codespaces).

Here are the reasons I like GitHub Codespaces:

- **Minimal fussing around with installations**: While I will provide instructions for directly using this code on macOS or Windows, you'll quickly see that getting your computer's setup right can be a pain in the neck. GitHub Codespaces allows you to designate a sort of "blueprint" for your development environment in the form of a `devcontainer.json` file. Actually, you'll see that in the `.devcontainer` folder of this repository, I've already created one of these "blueprint" files that is designed exactly for our Java / Vaadin work here.
- **Usable from any device with a web browser**: I personally like to use my iPad as my primary computer, but unfortunately, an iPad's operating system is not at all conducive for coding. GitHub Codespaces allows me to code from my iPad, which is a huge win for me. Additionally, I know a lot of students use Chromebooks, so as long as the school doesn't have this blocked, this is an excellent way for students to also code in a legit dev environment on their Chromebooks.
- **Fully fledged VS Code**: GitHub Codespaces is basically VS Code in a browser, and it's not a watered-down version of VS Code. You can install extensions, use the terminal, and do everything you'd expect to do in a normal VS Code environment. This also includes the allowance of GitHub Copilot (if you have the subscription for that).

Again, this tutorial will cover how to use Vaadin on macOS and Windows, but I highly recommend using GitHub Codespaces for students. It just makes things much simpler since installation can be one of the trickiest parts of coding. (And transparently, I struggled a lot with getting all the installations right on my Mac and Windows machines.)


## Installation Instructions
TBA



## Part 1: Creating the Vaadin UI