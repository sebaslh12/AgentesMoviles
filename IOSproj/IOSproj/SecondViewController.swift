//
//  SecondViewController.swift
//  IOSproj
//
//  Created by Estudiantes on 4/06/16.
//  Copyright © 2016 Estudiantes. All rights reserved.
//
// Messages view
import UIKit

class SecondViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    var from : Int = 0
    @IBOutlet weak var MessageList: UITableView!
    var to : Int = 0
    var MessagesGetDB : [Messages] = [Messages]()
    private var MessageDB = MessageManager()
    override func viewDidLoad() {
        super.viewDidLoad()
        self.MessagesGetDB = MessageDB.getMessage(self.from, to: self.to)
        print("Database lenght " + String(self.MessagesGetDB.count))
        super.viewDidLoad()
        let seconds  = 65.0
        let delay = seconds * Double(NSEC_PER_SEC)
        let dispatchtime = dispatch_time(DISPATCH_TIME_NOW,Int64(delay))
        
        dispatch_after(dispatchtime,dispatch_get_main_queue(), {
            self.viewWillAppear(true)
        })
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return MessagesGetDB.count
    }
    
    func tableView(tableView: UITableView,cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell{
        let cell = UITableViewCell(style: UITableViewCellStyle.Subtitle, reuseIdentifier: nil)
        //let cell = UITableViewCell()
        cell.textLabel!.text = self.MessagesGetDB[indexPath.row].text!
        return cell
    }
    override func viewWillAppear(animated: Bool) {
        MessageList.reloadData()
    }

}

