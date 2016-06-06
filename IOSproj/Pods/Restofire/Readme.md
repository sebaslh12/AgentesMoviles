![Restofire: A Protocol Oriented Networking Abstraction Layer in Swift](https://raw.githubusercontent.com/Restofire/Restofire/master/.github/restofire.png)

## Restofire

[![Platforms](https://img.shields.io/cocoapods/p/Restofire.svg)](https://cocoapods.org/pods/Restofire)
[![License](https://img.shields.io/cocoapods/l/Restofire.svg)](https://raw.githubusercontent.com/Restofire/Restofire/master/LICENSE)

[![Swift Package Manager](https://img.shields.io/badge/Swift%20Package%20Manager-compatible-brightgreen.svg)](https://github.com/apple/swift-package-manager)
[![Carthage compatible](https://img.shields.io/badge/Carthage-compatible-4BC51D.svg?style=flat)](https://github.com/Carthage/Carthage)
[![CocoaPods compatible](https://img.shields.io/cocoapods/v/Restofire.svg)](https://cocoapods.org/pods/Restofire)

[![Travis](https://img.shields.io/travis/Restofire/Restofire/master.svg)](https://travis-ci.org/Restofire/Restofire/branches)

[![Join the chat at https://gitter.im/Restofire/Restofire](https://badges.gitter.im/Restofire/Restofire.svg)](https://gitter.im/Restofire/Restofire?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Restofire is a protocol oriented networking abstraction layer in swift that is built on top of [Alamofire](https://github.com/Alamofire/Alamofire) to use services in a declartive way.

## Features

- [x] No Learning Curve
- [x] Default Configuration for Base URL / headers / parameters etc
- [x] Multiple Configurations
- [x] Single Request Configuration
- [x] Custom Response Serializer
- [x] Authentication
- [x] Response Validations
- [x] Request NSOperation
- [x] RequestEventuallyOperation with Auto Retry
- [x] [Complete Documentation](http://cocoadocs.org/docsets/Restofire)

## Requirements

- iOS 8.0+ / Mac OS X 10.9+ / tvOS 9.0+ / watchOS 2.0+
- Xcode 7.3+

## Installation

### CocoaPods

[CocoaPods](http://cocoapods.org) is a dependency manager for Cocoa projects. You can install it with the following command:

```bash
$ gem install cocoapods
```

> CocoaPods 0.39.0+ is required to build Restofire 1.1.0+.

To integrate Restofire into your Xcode project using CocoaPods, specify it in your `Podfile`:

```ruby
source 'https://github.com/CocoaPods/Specs.git'
platform :ios, '8.0'
use_frameworks!

pod 'Restofire', '~> 1.1'
```

Then, run the following command:

```bash
$ pod install
```

### Carthage

[Carthage](https://github.com/Carthage/Carthage) is a decentralized dependency manager that automates the process of adding frameworks to your Cocoa application.

You can install Carthage with [Homebrew](http://brew.sh/) using the following command:

```bash
$ brew update
$ brew install carthage
```

To integrate Restofire into your Xcode project using Carthage, specify it in your `Cartfile`:

```ogdl
github "RahulKatariya/Restofire" ~> 1.1
```
### Swift Package Manager

To use Restofire as a [Swift Package Manager](https://swift.org/package-manager/) package just add the following in your Package.swift file.

``` swift
import PackageDescription

let package = Package(
    name: "HelloRestofire",
    dependencies: [
        .Package(url: "https://github.com/Restofire/Restofire.git", versions: Version(1, 1, 0)..<Version(1, 2, 0))
    ]
)
```

## Usage

### Configuring Restofire

```swift
import Restofire

class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
        // Override point for customization after application launch.

        Restofire.defaultConfiguration.baseURL = "http://www.mocky.io/v2/"
        Restofire.defaultConfiguration.headers = ["Content-Type": "application/json"]
        Restofire.defaultConfiguration.logging = true
        Restofire.defaultConfiguration.authentication.credential = NSURLCredential(user: "user", password: "password", persistence: .ForSession)
        Restofire.defaultConfiguration.validation.acceptableStatusCodes = [200..<300]
        Restofire.defaultConfiguration.validation.acceptableContentTypes = ["application/json"]
        Restofire.defaultConfiguration.retry.retryErrorCodes = [NSURLErrorTimedOut,NSURLErrorNetworkConnectionLost]
        Restofire.defaultConfiguration.retry.retryInterval = 20
        Restofire.defaultConfiguration.retry.maxRetryAttempts = 10
        let sessionConfiguration = NSURLSessionConfiguration.defaultSessionConfiguration()
        sessionConfiguration.timeoutIntervalForRequest = 7
        sessionConfiguration.timeoutIntervalForResource = 7
        sessionConfiguration.HTTPAdditionalHeaders = Manager.defaultHTTPHeaders
        Restofire.defaultConfiguration.manager = Alamofire.Manager(configuration: sessionConfiguration)

        return true
  }

}
```

### Creating a Service

```swift
import Restofire

class PersonGETService: Requestable {

    typealias Model = [String: AnyObject]
    var path: String = "56c2cc70120000c12673f1b5"

}

```

### Consuming the Service

```swift
import Restofire

class ViewController: UIViewController {

    var person: [String: AnyObject]!
    var requestOp: RequestOperation<PersonGETService>!

    func getPerson() {
        requestOp = PersonGETService().executeTask() {
            if let value = $0.result.value {
                person = value
            }
        }
    }

    deinit {
        requestOp.cancel()
    }

}
```

## Examples

* [String Response Service](https://github.com/Restofire/Restofire/wiki/String-Response-Service-Example)

    ```json
    "Restofire is Awesome"
    ```
* [Int Response Service](https://github.com/Restofire/Restofire/wiki/Int-Response-Service-Example)

    ```json
    123456789
    ```
* [Float Response Service](https://github.com/Restofire/Restofire/wiki/Float-Response-Service-Example)

    ```json
    12345.6789
    ```
* [Bool Response Service](https://github.com/Restofire/Restofire/wiki/Bool-Response-Service-Example)

    ```json
    true
    ```
* [Void Response Service](https://github.com/Restofire/Restofire/wiki/Void-Response-Service-Example)

    ```json
    {}
    ```
* [Array Response Service](https://github.com/Restofire/Restofire/wiki/Array-Response-Service-Example)

    ```json
    ["Restofire","is","Awesome"]
    ```
* [JSON Response Service](https://github.com/Restofire/Restofire/wiki/JSON-Response-Service-Example)

    ```json
    {
      "id" : 12345,
      "name" : "Rahul Katariya"
    }
    ```
* [JSON Array Response Service](https://github.com/Restofire/Restofire/wiki/JSON-Array-Response-Service-Example)

    ```json
    [
      {
        "id" : 12345,
        "name" : "Rahul Katariya"
      },
      {
        "id" : 12346,
        "name" : "Aar Kay"
      }
    ]
    ```

## License

Restofire is released under the MIT license. See [LICENSE](https://github.com/Restofire/Restofire/blob/master/LICENSE) for details.
