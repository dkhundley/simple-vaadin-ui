![](assets/vaadin-banner.png)

# Building a Simple Java-Based UI with Vaadin
In this repository, I will share how you can build a simple Java-based UI with **Vaadin**. Vaadin is an open source framework that allows users to build a simple web-based user interface using their drag-and-drop builder.

To keep things easy and simple to understand, we're specifically going to be building a **pizza maker** (or, I guess, pizza order taker, if we're being specific). We're going to keep it simple by building only two screens, or *views*, which include:

1. A form for the user to build their pizza, including the size, crust, and toppings.
2. A confirmation screen that shows the user what they've ordered along with subtotal, tax, tip, and final total.

### Video Tutorial
If you're more of a visual learner, I completed a complementary video tutorial over on my YouTube channel [at this link](https://www.youtube.com/live/SzNTJ8aDEOo?si=3hrxXng3LYA1QRLS). The video covers a good chunk of the content in this text-based tutorial below, but isn't all inclusive of everything we cover in the text-based tutorial. I have also included timestamps in the description of the YouTube video if you would like to hop around to specific sections.

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
In the macOS section, we installed a tool called Homebrew to install our software dependencies. Unfortunately, Homebrew is not available on Windows, so instead, we'll be using an alternative called **Scoop** as our package manager. Scoop is similar to Homebrew for macOS and makes installing developer tools much easier. Here's how to get everything set up:

1. First, open PowerShell as Administrator (right-click and select "Run as Administrator"). (Note: PowerShell *should* be installed by default. If not, you should be able to download it from the Microsoft App Store.)

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
2. After creating your GitHub account, you should see a dashboard screen that looks like the one below. To create a new GitHub repository, click the green book-like "New" icon.

![](assets/github-create-new-repo.png)


### Visual Studio Code
In addition to installing all the software we've done so far, we will also need to ensure that we have installed **Visual Studio Code**, or **VS Code** for short. To be clear, when it comes to Java development, VS Code is actually NOT the number one recommended integrated development environment (IDE). That honor goes to **IntelliJ IDEA**. However, I'm recommending VS Code for this tutorial for a number of reasons. In short, VS Code is the most popular IDE in the world for software developers, and it's also the IDE that GitHub Codespaces uses. So, if you're following along with this tutorial, you'll need to have VS Code installed on your computer.

To install VS Code on macOS or Windows, all you have to do is visit the [official VS Code website](https://code.visualstudio.com/) and download the installer for your operating system. The installation process is pretty straightforward, and you should be up and running in no time. If you're using GitHub Codespaces, you don't need to worry about this step, as VS Code is already installed for you.


## Part 1: Creating the Vaadin UI
This section will largely take place in your web browser. We're going to be using Vaadin's drag-and-drop builder to create the UI for our pizza maker. If you've ever used a website builder like Wix or Squarespace, this is going to feel very similar. 

### The Vaadin Builder

![](assets/vaadin-builder.png)

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

### Loading the Vaadin Code into VS Code
We ended part 1 with a `.zip` file that contains all the Java code that Vaadin generated for us. We're going to want to load this code into VS Code so that we can start making alterations to it. Here's how you can do that:

1. Unzip the `.zip` file that you downloaded from Vaadin's builder.
2. Open VS Code on your computer. (If you're using GitHub Codespaces, you can skip this step, as VS Code is already open for you.)
3. Click on the "File" menu in the top left corner of VS Code.
4. Click on "Open Folder..."
5. Navigate to the unzipped folder that contains the Java code that Vaadin generated for you.
6. Click on the "Select Folder" button.

(Note: VS Code's UI also supports drag and dropping files into the window. So, if you prefer, you can also drag the unzipped folder into the VS Code window.)

You should now see all the files and folders that Vaadin generated for you in the sidebar of VS Code. If you're using GitHub Codespaces, you should see the same thing in the sidebar of your browser. It should look something like this:

![](assets/vscode-loaded-vaadin-code.png)

### Exploring the "Raw" Files
Before we go altering anything, let's take a moment for us to explore what Vaadin created for us. While almost every file in here is necessary, **we will NOT touch most of these files**. For brevity's sake and the fact that I am not a Java expect, assume that if I don't mention it, you shouldn't touch it.For this tutorial's sake, I will make reference to the raw contents of my files that I've saved in the `pizza-maker-raw` folder.

#### The `pizza-maker-raw` Folder
As we delve into this first layer of files, you'll see another subfolder called `src` alongside a number of other files. Generally speaking, software developers store their core "source" code in a folder called `src`, which as you can guess is short for "source." We'll revisit the `src` folder in a second.

The other files are what I largely refer to as "helper" files. For the most part, they aren't really necessary; however, the one file that is very important is `pom.xml`. This file is what Maven uses to manage all the dependencies for our project. If you're not familiar with Maven, think of it as a tool that helps us manage all the other tools and dependencies we'll need for our Java project.

We can ignore the other files here in `pizza-maker-raw`. Let's move into the `src` folder.

#### The `src` Folder
The `src` folder is where the "meat" of our project is stored. In the `src` folder, you'll see a number of subfolders and files. Again, all the files in here are necessary, but for the most part, we can ignore them. In fact, we only really care about one subfolder: we have to drill down to where our Java files are representing our two "views". In my case, these files are stored in `src/main/java/com/dkhundley/pizzamaker/views`.

When you finally get down to this `/views/` subfolder, you'll see two final sets of subfolders: one representing the order taker called `ordertaker` and the other representing the confirmation screen called `confirmationscreen`. Inside each of these respective subfolders are the Java files that build each view. **THESE ARE THE ONLY TWO JAVA FILES WE WILL BE ALTERING!!!** To keep things extremely simple, we are only going to be altering the content of these two files and nothing else. No alterations to any other files in `src`; no new file additions anywhere in `src`.

#### Exploring the Raw Order Taker Java File
Both the order taker and confirmation screen files are structured relatively similarly, so we're going to focus on just the order taker file. Let's break this file down, working our way from top to bottom.

At the top from lines 1-21, you'll notice we have a number of import statements. These import statements are bringing in extra code to help Java get its job done. The way I like to think about it is sort of like a smartphone. When you buy a new smartphone, it probably comes with a few default apps. But if you want to do something more, like browsing Instagram, playing Angry Birds, or something else, you'll have to download a third party app from the app store. Import statements are very much like smartphone apps. They bring in extra code that Java can use to do more things.

```java
package com.dkhundley.pizzamaker.views.ordertaker;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;
```

You'll notice that we're naturally importing a lot of components from Vaadin. This is based on the UI we built in Vaadin's builder. Later on when we start altering our code, we're going to have to revisit these imports because we'll need to add a few extra things to get everything working properly.

As we move down into lines 27-82, you'll see a series of lines that basically represent each of the components displayed on the UI.

```java
public OrderTakerView() {
        H1 h1 = new H1();
        H2 h2 = new H2();
        Hr hr = new Hr();
        H3 h3 = new H3();
        FormLayout formLayout3Col = new FormLayout();
        Select select = new Select();
        MultiSelectComboBox multiSelectComboBox = new MultiSelectComboBox();
        MultiSelectComboBox multiSelectComboBox2 = new MultiSelectComboBox();
        Hr hr2 = new Hr();
        H3 h32 = new H3();
        RadioButtonGroup radioGroup = new RadioButtonGroup();
        NumberField numberField = new NumberField();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Pizza Maker");
        h1.setWidth("max-content");
        h2.setText("Create your pizza below!");
        h2.setWidth("max-content");
        h3.setText("Select Your Toppings");
        h3.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        select.setLabel("Crust Size");
        select.setWidth("min-content");
        setSelectSampleData(select);
        multiSelectComboBox.setLabel("Meats");
        multiSelectComboBox.setWidth("min-content");
        setMultiSelectComboBoxSampleData(multiSelectComboBox);
        multiSelectComboBox2.setLabel("Veggies");
        multiSelectComboBox2.setWidth("min-content");
        setMultiSelectComboBoxSampleData(multiSelectComboBox2);
        h32.setText("Add a Tip?");
        h32.setWidth("max-content");
        radioGroup.setLabel("Percentage");
        radioGroup.setWidth("min-content");
        radioGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        numberField.setLabel("Custom Amount");
        numberField.setWidth("min-content");
        buttonPrimary.setText("Submit Order!");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h1);
        getContent().add(h2);
        getContent().add(hr);
        getContent().add(h3);
        getContent().add(formLayout3Col);
        formLayout3Col.add(select);
        formLayout3Col.add(multiSelectComboBox);
        formLayout3Col.add(multiSelectComboBox2);
        getContent().add(hr2);
        getContent().add(h32);
        getContent().add(radioGroup);
        getContent().add(numberField);
        getContent().add(buttonPrimary);
    }
```

The way this code renders on the web page follows the top-to-bottom order of these lines. For example, the first thing you'll see on the page is the `h1` header that says "Pizza Maker." The next thing you'll see is the `h2` header that says "Create your pizza below!" and so on. This is why the order of these lines is so important.

Frankly... I think this code is messy. It's not very clear nor readable, so we're going to come back in the next section with a tip on how to make this more workable.

Finally, at the bottom with lines 87-110, you'll see the following code:

```java
record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setMultiSelectComboBoxSampleData(MultiSelectComboBox multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
```

Let's first address that `record` piece. What this represents is a very simple means of creating a way to maintain data in Java. In this case, we're creating a `SampleItem` record that has three fields: `value`, `label`, and `disabled`. This is a very simple way to create a sort of "data structure" in Java. We won't be using this `record` code as is; we'll revisit this in a later section.

The `setSelectSampleData` and `setMultiSelectComboBoxSampleData` methods are used to populate the `Select` and `MultiSelectComboBox` components with data. This is a very common pattern in Java, and it's a way to keep our code clean and organized. You can imagine here that we'll want to update something like the dropdown selector to contain information about our pizza crust sizes and toppings. We'll also revisit this in a later section.



### Running the Vaadin UI for the First Time
Even though we haven't made any alterations to our code yet, it is still in a state where we can go ahead and run it right away. This section assumes you've already made it through the "Installation Instructions" section, so if you haven't, please go back and complete those steps. With those steps completed, every step, command, and piece of code going forward should all be the same regardless of what computer / platform you're using.

Given that this tutorial makes use of VS Code, I want to share how you can actually use the command line directly within VS Code. Per the screenshot below, if you click this icon, it will open up a tray at the bottom of the screen, with a number of tabs, one of these tabs being "Terminal". This is where you can run all the commands I'll be sharing in this tutorial. Additionally, you can also open / close this tray with the `cmd + j` keyboard shortcut.

![](assets/vscode-open-terminal-tray.png)

The Terminal tab is the command line where we will be running all our commands to actually create and build the Java project.

The first thing we'll need to do is to install our dependencies with the help of Maven. To do this, run the following command in the Terminal:

```bash
mvn clean install
```

Note that you only have to do this the first time we build the application. The only other time you may want to run this command is if you wanted to add additional dependencies down the road, but for the sake of this tutorial's simplicity, we won't be doing that. Also note that this process can take some time, upwards of 5 to 10 minutes.

After the `mvn clean install` command has completed, you'll notice that many new files have been added to your working repository, going has high as 10,000 files! You may have noticed that my repository here contains a `.gitignore` file.  This file tells GitHub which files I do NOT want uploaded from my local computer. This is why all these new files you have on your computer after running `mvn clean install` are not showing up in my repository. The best part is that you don't need to do anything at all with these new files. They're all part of the build process, and you can safely ignore them.

Now that we've installed our dependencies, we can run our application with the following command:

```bash
mvn spring-boot:run
```

The first time you run this, it can also take a while for the build process to complete. Not too long after the Spring Boot process begins doing its thing, VS Code will likely display some message to you that the application is running. You can then open up a web browser and navigate to `http://localhost:8080` to our application in action.

Again, the first build process takes some time, so most likely, when you go to `http://localhost:8080`, you'll see the following screen.

![](assets//vaadin-build-loading-screen.png)

This is perfectly normal. You'll just have to keep waiting some time, and eventually, the pizza making order taker screen will show up just as you'd expect.

In order to halt the application, go back to the Terminal tab in VS Code and press `ctrl + c` / `cmd + c`. This will stop the Spring Boot process, and you'll see a message in the Terminal that the process has been stopped.

From here going forward, this is exactly how we will begin our application every time we want to see our changes in action. Now, you might be wondering, "Is there some way to set an autoreload thing so that I can see my changes in real time?" I believe the answer is technically yes, but I could not get it to work. I tried a number of different things, but I could not get the application to autoreload. If you're able to get this working, I'd love to hear how you did it!



### Altering the Order Taker Java File
We're finally ready into moving into altering our Java code. We're going to start with the order taker file, and we're going to make a number of alterations to it.

#### ChatGPT Clean Code Tip
(Note: This ChatGPT section is recommended but not required. You're welcome to manually update your code to match closely to what the final pizza maker looks like.)

In the previous section when we looked at the code that Vaadin produces, we noted that the code is a little jumbled and not intuitive. That said, my first encouragement would be to leverage **ChatGPT** to help clean up the code. You don't need to have any paid subscription to do what I'm going to suggest. If you visit [ChatGPT's website](http://www.chatgpt.com), you are still able to make use of ChatGPT in a "logged out" fashion. Just a few things to keep in mind when using ChatGPT or any other Generative AI model:

- In order to get the best results from a Generative AI model, we provide instructions for what we want our final output to be. We refer to these instructions as **prompt engineering**. I will share a bit of prompt engineering down below specifically for our situation here.
- Be careful what data you give to ChatGPT. The free "logged out" version specifically notes that OpenAI will be allowed to train its future models on the data you give into it, so you definitely would not want to put in something like a credit card number or Social Security number. (In our situation, we're using pretty simple code, so I would not worry about using ChatGPT for this context.)
- ChatGPT will always produce a confident sounding response, but that doesn't mean this is always a correct response. We refer to these confident but incorrect responses as **hallucinations**. You'll want to check to make sure that ChatGPT doesn't do anything wild with your code, but I'll help you put curbs around that down below so that you'll most likely get good results.

```
Below, I am going to paste some code that I generated using Vaadin to support a user interface with a Java-based backend. The code is functional but not necessarily intuitive. Can you please help me to clean up this code? Please make the variable names more descriptive and also add annotated comments where it is appropriate. Do not make any alterations to the functionality of the code itself.

PASTE YOUR CODE HERE
```

#### Updating the Menu Selectors
TBA

#### Updating Other UI Elements
TBA

#### Calculating Subtotal and Total
TBA

#### Passing Data to the Confirmation Screen
TBA



### Altering the Confirmation Screen Java File
TBA

#### Loading the Data from the Order Taker
TBA

#### Viewing the Order Details from the Order Taker
TBA