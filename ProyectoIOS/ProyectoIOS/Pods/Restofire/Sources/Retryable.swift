//
//  Retryable.swift
//  Restofire
//
//  Created by Rahul Katariya on 23/04/16.
//  Copyright © 2016 AarKay. All rights reserved.
//

import Foundation

/// Represents a `Retry` that is associated with `Configurable`.
/// `configuration.retry` by default.
///
/// ### Create custom retryable
/// ```swift
/// protocol HTTPBinRetryable: Retryable { }
///
/// extension HTTPBinRetryable {
///
///   var retry: Retry {
///     var retry = Retry()
///     retry.retryErrorCodes = [NSURLErrorTimedOut,NSURLErrorNetworkConnectionLost]
///     retry.retryInterval = 20
///     retry.maxRetryAttempts = 10
///     return retry
///   }
///
/// }
/// ```
///
/// ### Using the above configurable
/// ```swift
/// class HTTPBinStringGETService: Requestable, HTTPBinRetryable {
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
public protocol Retryable {
    
    /// The Restofire configuration.
    var retry: Retry { get }
    
}

public extension Retryable where Self: Configurable {
    
    /// `configuration.retry`
    public var retry: Retry {
        return configuration.retry
    }
    
}
