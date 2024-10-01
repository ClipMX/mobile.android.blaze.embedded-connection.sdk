<a name="readme-top"></a>    
<br />
<div align="center">    
<a href="https://github.com/ClipMX/mobile.android.blaze.pinpad.sdk">    
<img src="https://assets-global.website-files.com/635aa55e76b13b5f73be2fe0/635ab4fc38f5e85102e32c6e_logo-clip-orange.svg" alt="Logo" width="80" height="80">    
</a>    
<h1 align="center">Embedded Connection SDK</h1>    
<a href="https://github.com/ClipMX/mobile.android.blaze.pinpad.sdk/actions"><img src="https://github.com/stripe/stripe-android/workflows/CI/badge.svg" alt="CI" style="max-width: 100%;"></a>    
<a href="https://github.com/ClipMX/mobile.android.blaze.pinpad.sdk/releases"><img src="https://img.shields.io/badge/release-1.0.0.1-orange" alt="GitHub release" data-canonical-src="https://img.shields.io/badge/release-1.0.7-orange?maxAge=60" style="max-width: 100%;"></a>    
<p align="center">    
📄 Embedded Connection SDK developer documentation 📄 <br />    
<br />    
</p>    
</div>

# About The Project

The Embedded Connection SDK offers a solution for use the peripheral hardware of POS Clip devices in your project.

# Table  of contents

- <a href="#clp-emc-sdk-getting-started">Getting started</a>
    - <a href="#getting-started-install-depedency">Installing dependency</a>
- <a href="#clp-emc-sdk-supported-devices">Supported devices</a>
- <a href="#clp-pr">Clip Printer</a>
    - <a href="#clp-pr-presentationr">Presentation</a>
    - <a href="#clp-pr-structure">Structure</a>
    - <a href="#clp-pr-examples">Examples</a>
    - <a href="#clp-pr-anatomy-and-properties">Anatomy and properties</a>
        - <a href="#clip-printer-builder-object-anatomy">ClipPrinter.Builder</a>
        - <a href="#clip-printer-object-anatomy">ClipPrinter</a>
        - <a href="#printable-content-builder-object-anatomy">PrintableContent.Builder</a>
        - <a href="#printable-object-anatomy">Printable</a>
            - <a href="#printable-section-object-anatomy">Section</a>
            - <a href="#printable-heading-object-anatomy">Heading</a>
            - <a href="#printable-divider-object-anatomy">Divider</a>
            - <a href="#printable-line-break-object-anatomy">Line break</a>
            - <a href="#printable-list-object-anatomy">List printable</a>
            - <a href="#printable-item-list-object-anatomy">Item List</a>
            - <a href="#printable-image-object-anatomy">Printable Image</a>
        - <a href="#clip-printer-font-size-anatomy">FontSize</a>
        - <a href="#clip-printer-text-alignment-anatomy">TextAlignment</a>
        - <a href="#clip-printer-listener-anatomy">ClipPrinterListener</a>
        - <a href="#clip-printer-error-anatomy">ClipPrinterError</a>
- <a href="#clp-emc-sdk-stay-updated">Stay updated</a>

<a  name="clp-emc-sdk-getting-started"></a>

# Getting started

To facilitate the integration testing process, follow the steps below to request a test POS device from our team:

**Reach Out to Us**

- Email: Contact our integration support team at sdk@clip.mx

**Provide Details**

- Your Information: Include your name, contact information, company name, and industry.

- Integration Requirements: Provide a brief overview of your integration requirements and goals.

- Timeline: Indicate your preferred timeline for testing and integration.

**Request a Test POS Device**

Mention in your email or message that you would like to request a test POS device for integration testing purposes. Our
team will review your request and get in touch to discuss further details.

**Arrange Shipment**

Once your request is approved, we will arrange for the shipment of a test POS device to your designated address.
Tracking information will be provided so you can monitor the delivery status.

<a  name="getting-started-install-depedency"></a>

## Installing dependency

- Add JitPack Repository: Open your settings file and add the JitPack repository to your Maven repositories list.

- Add Dependency: Open your build file and add the SDK dependency.

```gradle
 dependencyResolutionManagement {    
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)    
    repositories {    
	    mavenCentral()    
	    maven {
	        url = uri("https://jitpack.io")
	    }
	}
}    
```   

Example for adding the dependency into `build.gradle.kts` kotlin file.

```gradle 
dependencies {
	implementation("TBD")
}    
```  

<a  name="clp-emc-sdk-supported-devices"></a>

# Supported Devices

Embedded Connection SDK is supported on the following Clip Devices:

- Clip Total
- Clip Total2

<a  name="clp-pr"></a>

# Clip Printer

<a  name="clp-pr-presentation"></a>

## Presentation

Embedded Connection SDK allows you to integrate the POS Clip printer in your app in a friendly way.

If your app need print tickets, orders, summarizes or anything that you want, this solution fits for you.

<a  name="clp-pr-structure"></a>

## Structure

To make a successful implementation in your app we are going to introduce some concepts about how you can use the SDK.

### _ClipPrinter_

Is the responsible for printing content to a Clip POS, is your first contact with the printer. This object uses the
builder pattern for instantiation.

### _PrintableContent_

When you have an idea about what is that you want to print you will use this object. With the _PrintableContent_ you
will structure the format of your ticket using different _Printable_ components available for you in this SDK

### _Printable_

Is the base unit to build different structures of your ticket. You can mix all the _Printable_ provided and create
tickets according to your necessities.

The printable components available are the following:

| Printable name   | Description                                                                                                 |
|------------------|-------------------------------------------------------------------------------------------------------------|
| `Section`        | Represents a printable text section with formatting options and optional line breaks                        |
| `Heading`        | Represents a printable heading with text, alignment, font size, and optional top line breaks                |
| `Divider`        | Represents a printable divider, which can be a simple line or a line with a centered title                  |
| `LineBreak`      | Represents a printable line break, adding vertical space to the printed output                              |
| `ListPrintable`  | Represents a printable list of `ItemList`, where each item can be a single line of text or a two-column row |
| `ItemList`       | Represents a printable item list that can be either a single line of text or a two-column row               |
| `PrintableImage` | Represents a printable image                                                                                |

<a  name="clp-pr-examples"></a>

## Examples

### Basic Example

The printer implementation can be summarized in 3 steps:

1. Create `ClipPrinter` object.
2. Customize the `PrintableContent`.
3. Call `ClipPrinter.print()` function.

```kotlin
fun print(context: Context) {

// STEP 1.
// Create CliPrinter using builder pattern.
    clipPrinter = ClipPrinter.Builder().setContext(context).build()

// STEP 2.
// Create image variables for example purposes:
// One image for header and last image for print in ticket body.
    val myHeaderImage =
        context.getDrawable(R.drawable.ic_food_restaurant_foreground)?.toBitmap()
    val myPrintableImage = context.getDrawable(R.drawable.ic_launcher_foreground)?.toBitmap()

    /* Create the PrintableContent using builder pattern.
     The printableContent structure is divided in three parts: Header (Optional), Body and Footer(Optional).
     In this example the body has two printable components: Section and Divider.
     */
    val myPrintableContent =
        PrintableContent.Builder()
            .setHeader("CLIP PRINTER SDK", myHeaderImage)
            .addPrintable(
                Section(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing",
                    alignment = TextAlignment.CENTER,
                )
            ).addPrintable(Divider())
            .addPrintable(PrintableImage(myPrintableImage!!))
            .setFooter(
                listOf(
                    "Aplican términos y condiciones",
                    "Conserve el ticket para futuras aclaraciones",
                    "clip.mx",
                )
            )
            .build()

// STEP 3.
// The print function receive two parameters: The printableContent and a ClipPrinterListener.
    clipPrinter.print(
        myPrintableContent,
        object : ClipPrinterListener {
            override fun onSuccessfulPrint() {
                //TODO: Add printing success action
            }

            override fun onFailedPrint(clipPrinterError: ClipPrinterError) {
                //TODO: Add printing error action
            }
        },
    )

}
```

### Example for printing Lists

You can implement lists in your ticket with different parameters of customization.
This section will show you an overview about how create items for you list.

We can create items for simulate a two-columns row:

```kotlin
val itemList =
    ListPrintable(
        listOf(
            ItemList(rowContent = RowContent("U. Producto", "Precio", isListHeader = true)),
            ItemList(rowContent = RowContent("1 Milk", "$34")),
            ItemList(rowContent = RowContent("1 Orange", "$34")),
            ItemList("Netus luctus massa habitasse"),
            ItemList("Interdum eleifend iaculis;"),
            ItemList("Rhoncus donec quam torquent"),
            ItemList(rowContent = RowContent("1 Coffee", "$34")),
        ),
    )
```

In this example we pass to `ListPrintable` object an `ItemList` list.
For this example is created an `ItemList` in two different ways:

- Creating a single `ItemList`: Only will show the text passed to constructor.
- Passing a `RowContent`: With the purpose to simulate two columns in the ticket (like a table).

As you can see, its possible mix in the same `ListPrintable` different instances of an `ItemList`.

Also, you can add a divider that will apply for all elements in your ListPrintable:

```kotlin
val itemListWithDivider =
    ListPrintable(
        listOf(
            ItemList(rowContent = RowContent("Left", "Right", isListHeader = true)),
            ItemList(rowContent = RowContent("1 Product", "$34")),
            ItemList(rowContent = RowContent("1 Product", "$34")),
            ItemList("Netus luctus massa habitasse"),
            ItemList("Interdum eleifend iaculis;"),
            ItemList("Rhoncus donec quam torquent"),
        ),
        divider = Divider()
    )
```

With our `itemList` and `itemListWithDivider` variables we can finish the printing process:

```kotlin
val myPrintableContentList = PrintableContent.Builder()
    .addPrintable(Heading(text = "My first list"))
    .addPrintable(itemList)
    .addPrintable(Heading(text = "My second list with dividers"))
    .addPrintable(itemListWithDivider).build()

clipPrinter.print(myPrintableContentList, object : ClipPrinterListener {
    override fun onSuccessfulPrint() {
        TODO("Not yet implemented")
    }

    override fun onFailedPrint(clipPrinterError: ClipPrinterError) {
        TODO("Not yet implemented")
    }
})
```

### Example using all components

<img src="https://lh7-rt.googleusercontent.com/docsz/AD_4nXd2xcajuhAyGlHL_Vq440sm78zaq_33hjztaPNyXfH0IO9Lk9ndX7zOVhTCoTNlQEQZ5Zar_Cc87yk-zN00V2p7HZzW2BNDBmjRVMPrK7PO6v8FCeyz2hjL425JQ-TfguJIMuHfyk4AkTVYkrt-4TPR0Es?key=gc6WOPNG8yUyIZYN2Fwh-A" width="30%"/>

```kotlin
private val itemList =
    ListPrintable(
        listOf(
            ItemList(rowContent = RowContent("U. Producto", "Precio", isListHeader = true)),
            ItemList(rowContent = RowContent("1 Milk", "$34")),
            ItemList(rowContent = RowContent("1 Orange", "$34")),
            ItemList("Netus luctus massa habitasse"),
            ItemList("Interdum eleifend iaculis;"),
            ItemList("Rhoncus donec quam torquent"),
            ItemList(rowContent = RowContent("1 Coffee", "$34")),
        ),
    )

private val itemListWithDivider =
    ListPrintable(
        listOf(
            ItemList(rowContent = RowContent("Left", "Right", isListHeader = true)),
            ItemList(rowContent = RowContent("1 Product", "$34")),
            ItemList(rowContent = RowContent("1 Product", "$34")),
            ItemList("Netus luctus massa habitasse"),
            ItemList("Interdum eleifend iaculis;"),
            ItemList("Rhoncus donec quam torquent"),
        ),
        divider = Divider(),
    )

private val footerList =
    listOf(
        "Aplican términos y condiciones",
        "Conserve el ticket para futuras aclaraciones",
        "clip.mx",
    )

private fun printableContent(
    bitmap: Bitmap?,
    secondImage: Bitmap?,
): PrintableContent =
    PrintableContent
        .Builder()
        .setHeader("CLIP PRINTER SDK", bitmap)
        .addPrintable(
            Section(
                text = "Total:",
                alignment = TextAlignment.CENTER,
            ),
        ).addPrintable(
            Heading(
                "$25.00",
                alignment = TextAlignment.CENTER,
                fontSize = FontSize.SUPER_LARGE,
            ),
        ).addPrintable(
            Heading(
                text = "VENTA",
                alignment = TextAlignment.CENTER,
                fontSize = FontSize.MEDIUM,
            ),
        ).addPrintable(
            Section(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing",
                alignment = TextAlignment.CENTER,
            ),
        ).addPrintable(Divider())
        .addPrintable(
            Heading(
                text = "TRANSACCIÓN APROBADA",
                alignment = TextAlignment.CENTER,
                lineBreaksTop = 1,
            ),
        )
        .addPrintable(Divider('#'))
        .addPrintable(PrintableImage(secondImage!!))
        .addPrintable(Divider(title = "COPY OF RECEIPT"))
        .addPrintable(itemListWithDivider)
        .addPrintable(LineBreak(4))
        .addPrintable(itemList)
        .setFooter(footerList)
        .build()
```

<a  name="clp-pr-anatomy-and-properties"></a>

## Anatomy and properties

<a  name="clip-printer-builder-object-anatomy"></a>

### `ClipPrinter.Builder`

<br>

```kotlin
fun setContext(context: Context): Builder
```

Use the context required for building the printer

| Parameters |                     |
|------------|---------------------|
| `context`  | The Android context |

<br>

```kotlin
fun build(): ClipPrinter
```

Sets up the reader device and creates a `ClipPrinter` using the provided context.

- Throws `ClipPrinterNotBuiltException` if no Clip reader with a printer is detected or if the printer cannot be built.
- Throws `ContextNotInitializedException` if the provided context is null.

<br><br>

<a  name="clip-printer-object-anatomy"></a>

### `ClipPrinter`

```kotlin
fun print(printableContent: PrintableContent, clipPrinterListener: ClipPrinterListener)
```

Prints the provided `PrintableContent` to the printer

| Parameters            |                                                                                                                               |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------|
| `printableContent`    | The content to be printed                                                                                                     |
| `clipPrinterListener` | A listener to receive the print result. This listener will be notified when the print operation is successful or if it fails. |

<br><br>

<a  name="printable-content-builder-object-anatomy"></a>

### `PrintableContent.Builder`

```kotlin
fun setHeader(title: String, image: Bitmap): Builder
```

Sets the header for the printable content. Set a header is **optional**

| Parameters |                                                    |
|------------|----------------------------------------------------|
| `title`    | The title of the header.                           |
| `image`    | An optional bitmap image to include in the header. |

<br>

```kotlin
fun addPrintable(printable: Printable): Builder
```

Adds a printable item to the list.

| Parameters  |                            |
|-------------|----------------------------|
| `printable` | The printable item to add. |

<br>

```kotlin
fun setFooter(texts: List<String>): Builder
```

Sets the footer for the printable content.

| Parameters |                                             |
|------------|---------------------------------------------|
| `texts`    | A list of strings to include in the footer. |

<br>

```kotlin
fun build(): PrintableContent
```

Creates a `PrintableContent` instance using the configured values.

<br><br>

<a  name="printable-object-anatomy"></a>

### Printable

The following objects are components where you will be able to build and customize the structure of your tickets.

<br>

<a  name="printable-section-object-anatomy"></a>

### Section

```kotlin
class Section(text: String, lineBreaks: Int, alignment: TextAlignment, fontSize: FontSize, boldEnabled: Boolean)
```

Represents a printable text section with formatting options and optional line breaks.

| Attributes    |                                                                           |
|---------------|---------------------------------------------------------------------------|
| `texts`       | The text content of the section.                                          |
| `lineBreaks`  | The number of line breaks to add after the section. Default is 0.         |
| `alignment`   | The text alignment for the section. Default is `TextAlignment.LEFT`       |
| `fontSize`    | The font size for the section. Default is `FontSize.MEDIUM`.              |
| `boldEnabled` | Indicates whether the text should be printed in bold. Default is `false`. |

<br>

<a  name="printable-heading-object-anatomy"></a>

### Heading

```kotlin
class Heading(text: String, alignment: TextAlignment, fontSize: FontSize, lineBreaksTop: Int)
```

Represents a printable heading with text, alignment, font size, and optional top line breaks.

| Attributes      |                                                                      |
|-----------------|----------------------------------------------------------------------|
| `text`          | The text content of the heading.                                     |
| `alignment`     | The text alignment for the heading. Default is `TextAlignment.LEFT`. |
| `fontSize`      | The font size for the heading. Default is `FontSize.LARGE`.          |
| `lineBreaksTop` | The number of line breaks to add before the heading. Default is 0.   |

<br>

<a  name="printable-divider-object-anatomy"></a>

### Divider

```kotlin
class Divider(divider: Char, title: String)
```

Represents a printable divider, which can be a simple line or a line with a centered title.

| Attributes |                                                                       |
|------------|-----------------------------------------------------------------------|
| `divider`  | The character used to create the divider line. Default is `-`.        |
| `title`    | An optional title to be centered within the divider. Default is null. |

<br>

<a  name="printable-line-break-object-anatomy"></a>

### Line break

```kotlin
class LineBreak(spaces: Int)
```

Represents a printable line break, adding vertical space to the printed output.

| Attributes |                                                 |
|------------|-------------------------------------------------|
| `spaces`   | The number of line breaks to add. Default is 1. |

<br>

<a  name="printable-list-object-anatomy"></a>

### List Printable

```kotlin
class ListPrintable(items: List<ItemList>, bulletSpan: Char, divider: Divider)
```

Represents a printable list of items, where each item can be a single line of text or a two-column row.
Items can be optionally prefixed with a bullet point and separated by a divider.

| Attributes   |                                                                         |
|--------------|-------------------------------------------------------------------------|
| `items`      | The list of `ItemList` objects to be printed.                           |
| `bulletSpan` | The character used for the bullet point. Default is `*`.                |
| `divider`    | An optional `Divider` to be printed after each item. Default is `null`. |

<br>

<a  name="printable-item-list-object-anatomy"></a>

### Item List

```kotlin
class ItemList(text: String, boldEnabled: Boolean, rowContent: RowContent)
```

Represents a printable item list that can be either a single line of text or a two-column row.

| Attributes    |                                                                           |
|---------------|---------------------------------------------------------------------------|
| `text`        | The text to be printed as a single line. Default is an empty string.      |
| `boldEnabled` | Indicates whether the text should be printed in bold. Default is `false`. |
| `rowContent`  | The content of the two-column row. Default is `null`.                     |

- Throw `ItemListContentException` if both `text` and `rowContent` are provided.

<br>

```kotlin
class RowContent(textColumn1: String, textColumn2: String, isListHeader: Boolean)
```

Represents the content of a two-column row for printing.

| Attributes     |                                                                        |
|----------------|------------------------------------------------------------------------|
| `textColumn1`  | The text content for the first column.                                 |
| `textColumn2`  | The text content for the second column.                                |
| `isListHeader` | Indicates whether this row is a header for a list. Default is `false`. |

<br>

<a  name="printable-image-object-anatomy"></a>

### Printable image

```kotlin
class PrintableImage(bitmapResource: Bitmap)
```

Represents a printable image provided by your project resources.

| Attributes       |                                 |
|------------------|---------------------------------|
| `bitmapResource` | The bitmap image to be printed. |

<br><br>

<a  name="clip-printer-font-size-anatomy"></a>

### `FontSize`

For the vast majority of `Printable` components you can customize the font size. You can use the following fontSize:

- `EXTRA_SMALL`
- `SMALL`
- `MEDIUM`
- `LARGE`
- `EXTRA_LARGE`
- `SUPER_LARGE`

<br><br>

<a  name="clip-printer-text-alignment-anatomy"></a>

### `TextAlignment`

Same as the FontSize, you can customize the text alignment of your `Printable` components. You can use the following
types:

- `LEFT`
- `CENTER`
- `RIGHT`

<br><br>
<a  name="clip-printer-listener-anatomy"></a>

### `ClipPrinterListener`

```kotlin
interface ClipPrinterListener {

    fun onSuccessfulPrint()

    fun onFailedPrint(clipPrinterError: ClipPrinterError)
}
```

A listener interface for receiving events related to clip printing.
Implement this interface to be notified of the success or failure of a clip printing operation.

| Method                                              |                                              |
|-----------------------------------------------------|----------------------------------------------|
| `onSuccessfulPrint()`                               | Called when a clip is successfully printed.  |
| `onFailedPrint(clipPrinterError: ClipPrinterError)` | Called when a clip printing operation fails. |

<br><br>

<a  name="clip-printer-error-anatomy"></a>

### `ClipPrinterError`

If the `ClipPrinterListener` calls  `onFailedPrint()` you will receive a `ClipPrinterError`. These error are described
in the following table:

| ClipPrinterError     | Description                                           |
|----------------------|-------------------------------------------------------|
| `PRINT_FAIL`         | General printing failure.                             |
| `ADD_STRING_FAIL`    | Failed to add a string to the print buffer.           |
| `ADD_IMAGE_FAIL`     | Failed to add an image to the print buffer.           |
| `PRINTER_BUSY`       | The printer is currently busy with another operation. |
| `PAPER_LACK`         | The printer is out of paper.                          |
| `WRONG_PACKAGE`      | Incorrect or incompatible printer driver or package.  |
| `PRINTER_FAULT`      | A general printer fault has occurred.                 |
| `PRINTER_TOO_HOT`    | The printer has overheated.                           |
| `PRINTER_UNFINISHED` | A previous printing operation was not completed.      |
| `NO_PRINTER_FOUND`   | No compatible printer was found.                      |  
| `UNKNOWN`            | An unknown error occurred.                            |

<a  name="clp-emc-sdk-stay-updated"></a>

# Stay updated

We strongly recommend staying informed about new versions of our SDK and updating your library frequently.
While we strive to provide clean updates with no breaking changes,
keeping your SDK version up-to-date ensures you have access to the latest features and improvements.

And that's it! With these steps, you can have fun with the POS Clip in your application.