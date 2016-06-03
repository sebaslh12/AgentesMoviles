//
//  Messages.swift
//  ProyectoIOS
//
//  Created by Estudiantes on 2/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class Messages {
    var id:Int
    var from:Int
    var to:Int
    var text:String
    var date:String
    
    init(id:Int, from:Int, to:Int, text:String, date:String){
        self.id = id
        self.from = from
        self.to = to
        self.text = text
        self.date = date
    }    
}