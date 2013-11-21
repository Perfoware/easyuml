package org.uml.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Implementation of components that can be added to class diagram - classes, interfaces or enums. Relations are not
 * implemented in this but in class RelationComponent
 * @author zoran
 * @see ClassComponent
 * @see RelationComponent
 */
public class ClassDiagramComponent implements Serializable {
    
    private String name;     // class, interface or enum name              
    protected HashMap<String,Member> members; // index of all fields, methods and constructors
    protected ClassDiagram parentDiagram;            
    private int memberCounter = 0;
            
    /**
     * Default constructor. Initializes only memebers (fields, methods and constructors)
     */
    public ClassDiagramComponent() {
        members = new HashMap<String, Member>(); 
    }
    
    /**
     * Constructor that initializes only name of ClassDiagramComponent.
     * @param name of component
     */
    public ClassDiagramComponent(String name) {
        this.name = name;
    }
      
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
    
    /**
     * Adds member of ClassDiagramComponent to a members collection. Similar implementation as in ClassDiagram.
     * @param member
     * @see ClassDiagram#addComponent(org.uml.model.ClassDiagramComponent) 
     */
    protected void addMember(Member member) {
        if(nameExists(member.getName())){
            member.setName(member.getName() + memberCounter);
        }
        memberCounter++;
        members.put(member.getName(), member);
    }
    
    /**
     * Removes member of ClassDiagramComponent from collection of components.
     * @param name of component that will be removed
     */
    public void removeMember(String name) {
        members.remove(name);
    }
    
    public ClassDiagram getParentDiagram() {
        return parentDiagram;
    }

    public void setParentDiagram(ClassDiagram parentDiagram) {
        this.parentDiagram = parentDiagram;
    }
    
    /**
     * Checks if member of ClassDiagramComponent already exists in collection of components.
     * @param name
     * @return 
     */
    public boolean nameExists(String name) {
        return members.containsKey(name);
    }
    
    /**
     * Removes member from ClassDiagramComponent's collection of members and replaces it with same member but with new name.
     * @param member that will be renamed
     * @param oldName old name of that component
     */
    public void componentNameChanged(Member member, String oldName) {
        members.remove(oldName);
        addMember(member);
    }

    public HashMap<String, Member> getMembers() {
        return members;
    }

    
}
