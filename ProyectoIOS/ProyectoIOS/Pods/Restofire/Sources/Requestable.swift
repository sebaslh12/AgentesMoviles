//
//  Requestable.swift
//  Restofire
//
//  Created by Rahul Katariya on 24/03/16.
//  Copyright © 2016 AarKay. All rights reserved.
//

import Foundation
import Alamofire

/// Represents an HTTP Request that can be asynchronously executed. You must 
/// provide a `path`.
///
/// ### Creating a request.
/// ```swift
/// import Restofire
///
/// struct PersonPOSTService: Requestable {
///
///   let path: String
///   let method: Alamofire.Method = .POST
///   let parameters: AnyObject?
///
///   init(id: String, parameters: AnyObject? = nil) {
///     self.path = "person/\(id)"
///     self.parameters = parameters
///   }
///
/// }
/// ```
///
/// ### Consuming the request.
/// ```swift
/// import Restofire
///
/// class ViewController: UIViewController  {
///
///   var request: Request!
///   var person: AnyObject!
///
///   func createPerson() {
///     request = PersonPOSTService(id: "123456789", parameters: person).executeTask()
///   }
///
///   deinit {
///     request.cancel()
///   }
///
/// }
/// ```
public protocol Requestable: Configurable, ResponseSerializable, Authenticable, Validatable, Retryable {
    
    /// The type of object returned in response.
    associatedtype Model
    
    /// The base URL.
    var baseURL: String { get }
    
    /// The path relative to base URL.
    var path: String { get }
    
    /// The HTTP Method.
    var method: Alamofire.Method { get }
    
    /// The request parameter encoding.
    var encoding: Alamofire.ParameterEncoding { get }
    
    /// The HTTP headers.
    var headers: [String : String]? { get }
    
    /// The request parameters.
    var parameters: AnyObject? { get }
    
    /// The logging.
    var logging: Bool { get }
    
    /// The Alamofire Manager.
    var manager: Alamofire.Manager { get }
    
    /// The queue on which reponse will be delivered.
    var queue: dispatch_queue_t? { get }
    
    /// The credential.
    var credential: NSURLCredential? { get }
    
    /// The Alamofire validation.
    var validation: Request.Validation? { get }
    
    /// The acceptable status codes.
    var acceptableStatusCodes: [Range<Int>]? { get }
    
    /// The acceptable content types.
    var acceptableContentTypes: [String]? { get }
    
    /// The retry error codes.
    var retryErrorCodes: Set<Int> { get }
    
    /// The retry interval.
    var retryInterval: NSTimeInterval { get }
    
    /// The max retry attempts.
    var maxRetryAttempts: Int { get }
    
    /// Called when the Request starts.
    func didStartRequest()
    
    /// Called when the Request succeeds.
    ///
    /// - parameter response: The Alamofire Response
    func didCompleteRequestWithResponse(response: Response<Self.Model, NSError>)
    
}

public extension Requestable {
    
    /// Creates a `RequestOperation` for the specified `Requestable` object and
    /// asynchronously executes it.
    ///
    /// - parameter completionHandler: A closure to be executed once the request
    ///                                has finished. `nil` by default.
    ///
    /// - returns: The created `RequestOperation`.
    public func executeTask(completionHandler: (Response<Model, NSError> -> Void)? = nil) -> RequestOperation<Self> {
        let rq = requestOperation(completionHandler)
        rq.start()
        return rq
    }
    
    /// Creates a `RequestOperation` for the specified `Requestable` object.
    ///
    /// - parameter completionHandler: A closure to be executed once the operation
    ///                                is started and the request has finished.
    ///                                `nil` by default.
    ///
    /// - returns: The created `RequestOperation`.
    public func requestOperation(completionHandler: (Response<Model, NSError> -> Void)? = nil) -> RequestOperation<Self> {
        let requestOperation = RequestOperation(requestable: self, completionHandler: completionHandler)
        return requestOperation
    }
    
    #if !os(watchOS)
    
    /// Creates a `RequestEventuallyOperation` for the specified `Requestable`
    /// object and asynchronously executes it when internet is reachable.
    ///
    /// - parameter completionHandler: A closure to be executed once the request
    ///                                has finished. `nil` by default.
    ///
    /// - returns: The created `RequestEventuallyOperation`.
    public func executeTaskEventually(completionHandler: (Response<Model, NSError> -> Void)? = nil) -> RequestEventuallyOperation<Self> {
        let req = requestEventuallyOperation(completionHandler)
        Restofire.defaultRequestEventuallyQueue.addOperation(req)
        return req
    }
    
    /// Creates a `RequestEventuallyOperation` for the specified requestable object.
    ///
    /// - parameter completionHandler: A closure to be executed once the operation
    ///                                is started and the request has finished.
    ///                                `nil` by default.
    ///
    /// - returns: The created `RequestEventuallyOperation`.
    public func requestEventuallyOperation(completionHandler: (Response<Model, NSError> -> Void)? = nil) -> RequestEventuallyOperation<Self> {
        let requestEventuallyOperation = RequestEventuallyOperation(requestable: self, completionHandler: completionHandler)
        return requestEventuallyOperation
    }
    
    #endif
    
    /// Does nothing.
    func didStartRequest() { }
    
    /// Does nothing.
    func didCompleteRequestWithResponse(response: Response<Self.Model, NSError>) { }
    
}

// MARK: - Default Implementation
public extension Requestable {
    
    /// `configuration.BaseURL`
    public var baseURL: String {
        return configuration.baseURL
    }
    
    /// `configuration.method`
    public var method: Alamofire.Method {
        return configuration.method
    }
    
    /// `configuration.encoding`
    public var encoding: Alamofire.ParameterEncoding {
        return configuration.encoding
    }
    
    /// `configuration.headers`
    public var headers: [String: String]? {
        return configuration.headers
    }
    
    /// `nil`
    public var parameters: AnyObject? {
        return nil
    }
    
    /// `configuration.logging`
    public var logging: Bool {
        return configuration.logging
    }
    
    /// `configuration.manager`
    public var manager: Alamofire.Manager {
        return configuration.manager
    }
    
    /// `configuration.queue`
    public var queue: dispatch_queue_t? {
        return configuration.queue
    }
    
    /// `authentication.credential`
    public var credential: NSURLCredential? {
        return authentication.credential
    }
    
    /// `validation.validation`
    public var validation: Request.Validation? {
        return validation.validation
    }
    
    /// `validation.acceptableStatusCodes`
    public var acceptableStatusCodes: [Range<Int>]? {
        return validation.acceptableStatusCodes
    }
    
    /// `validation.acceptableContentTypes`
    public var acceptableContentTypes: [String]? {
        return validation.acceptableContentTypes
    }
    
    /// `retry.retryErrorCodes`
    public var retryErrorCodes: Set<Int> {
        return retry.retryErrorCodes
    }
    
    /// `retry.retryInterval`
    public var retryInterval: NSTimeInterval {
        return retry.retryInterval
    }
    
    /// `retry.maxRetryAttempts`
    public var maxRetryAttempts: Int {
        return retry.maxRetryAttempts
    }
    
}
