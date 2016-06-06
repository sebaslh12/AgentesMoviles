//
//  Configurable.swift
//  Restofire
//
//  Created by Rahul Katariya on 15/04/16.
//  Copyright © 2016 AarKay. All rights reserved.
//

import Foundation

/// Represents a `Configuration` that is associated with `Requestable`.
/// `Restofire.defaultConfiguration()` by default.
///
/// ### Create custom configurable
/// ```swift
/// protocol HTTPBinConfigurable: Configurable { }
///
/// extension HTTPBinConfigurable {
///
///   var configuration: Configuration {
///     var config = Configuration()
///     config.baseURL = "https://httpbin.org/"
///     config.logging = true
///     return config
///   }
///
/// }
/// ```
///
/// ### Using the above configurable
/// ```swift
/// class HTTPBinStringGETService: Requestable, HTTPBinConfigurable {
///
///   let path: String = "get"
///   let encoding: ParameterEncoding = .URLEncodedInURL
///   var parameters: AnyObject?
///
///   init(parameters: AnyObject?) {
///     self.parameters = parameters
///   }
///
/// }
/// ```
public protocol Configurable {
    
    /// The Restofire configuration.
    var configuration: Configuration { get }

}

public extension Configurable where Self: Requestable {
    
    /// `Restofire.defaultConfiguration`
    public var configuration: Configuration {
        return Restofire.defaultConfiguration
    }
    
}
