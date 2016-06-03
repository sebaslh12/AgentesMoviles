//
//  ContactViewController.swift
//  ProyectoIOS
//
//  Created by Estudiantes on 2/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//

import UIKit

class ContactViewController: UIViewController{
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        var MessageDest : FirstViewController = segue.destinationViewController as! FirstViewController
        
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?, ) {
        var FilesDest : SecondViewController = segue.destinationViewController as! SecondViewController
        
    }
    
    
    
}

