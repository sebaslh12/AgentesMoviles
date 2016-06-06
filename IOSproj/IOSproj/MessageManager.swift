//
//  MessageManager.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit
import SQLite
public class MessageManager {
    private var db:Connection
    
    private var messages:Table
    private var from_M:Expression<Int>
    private var to_M:Expression<Int>
    private var text:Expression<String>
    private var id:Expression<Int>
    
    init(){
        let path = NSSearchPathForDirectoriesInDomains(.DocumentDirectory, .UserDomainMask, true).first!
        
        db = try! Connection("\(path)/db.sqlite3")
        messages = Table("message")
        id = Expression<Int>("id")
        from_M = Expression<Int>("from_M")
        to_M = Expression<Int>("to_M")
        text = Expression<String>("text")
        
        //try! db.run(tasks.drop(ifExists: true))
        try! db.run(messages.create(ifNotExists: true) { t in
            t.column(id, primaryKey: PrimaryKey.Autoincrement)
            t.column(from_M)
            t.column(to_M)
            t.column(text)
            })
    }
    
    func addMessage(m:Messages) {
        try! db.run(messages.insert(from_M <- m.from!, to_M <- m.to!, text <- m.text!))
    }
    
    func getMessage(from:Int,to:Int) -> [Messages]{
        //Select * from messages
        let query = messages.select(text)
            .filter(from_M == from && to_M == to)
        
        let men = Array(try! db.prepare(query))
        var mreturn : [Messages] = [Messages]()
        for m in men{
            mreturn.append(Messages(from: m.get(from_M), to:m.get(to_M),text: m.get(text)))
            print(m.get(text))
        }
        return mreturn
        //return Messages(from: men.get(fromColumn), to:men.get(toColumn),text: men.get(textColumn));
    }
    
    
    func count() -> Int {
        return db.scalar(messages.count)
    }
}