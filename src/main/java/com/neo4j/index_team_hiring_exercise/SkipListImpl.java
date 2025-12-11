package com.neo4j.index_team_hiring_exercise;

import java.util.Random;

public class SkipListImpl implements SkipList {

    final float p  = 0.5f;
    int probabilityPercentage = Math.round(p*100);

    private int nodeCount = 0;
    private Node start = null;

    private Node head = new Node(null,Long.MIN_VALUE);
    private Node tail = null;
    private Node lastNodeFound = null;
    boolean valueNotFound = true;

    private Random random = new Random();

    public SkipListImpl() {
        head = new Node(null,Long.MIN_VALUE);
        tail = new Node(null,Long.MAX_VALUE);
        head.setBelow(start);
    }

    @Override
    public boolean add(long value) {
        Node nodeFound =null;
        Node newNode;
        if(start==null){
            start = new Node(null,value);
            head.setBelow(start);
            start.setPrevious(head);
            lastNodeFound = head;
            nodeCount++;
        }else{
                 nodeFound = skipSearch(value);
                //nodeFound = lastNodeFound;
                if (valueNotFound) {
                    newNode = insertNode(nodeFound, value);
                    if (nodeCount == 1) {
                        start = newNode;
                    }
                }
        }
        return valueNotFound;
    }

    private boolean randomBoolean() {
        Random rand = new Random();
        int num=rand.nextInt(100);
        return num < probabilityPercentage;
    }

    private Node insertNode(Node nodeFound, long value) {

        Node newTargetNode = new Node(null,value);


        //if we found a node with a lower value
        if ((nodeFound!=null && nodeFound.getValue()!=value)){

            // insert a new target node into the skipList after the node found
            newTargetNode.setPrevious(nodeFound);
            newTargetNode.setNext(nodeFound.getNext());

            if (nodeFound==head) {
              newTargetNode.setNext(head.getBelow());
                //newTargetNode.setPrevious(head);
                head.setBelow(newTargetNode);
                start = newTargetNode;

            } else{
                nodeFound.setNext(newTargetNode);

            }
            nodeCount++;

            // iterate through a random number of higher levels inserting "tall" nodes
            // of the current value above the new target node (to help speed future searches)
            while(randomBoolean()){

                //find a previous node which has a tall node above it
                while(nodeFound!=null&&nodeFound.getAbove()==null) {
                    nodeFound = nodeFound.getPrevious();
                }

                // if one exists then insert a new tall node for the target and link it to the previous
                // tall node
                if (nodeFound!=null) {
                    nodeFound = nodeFound.getAbove();
                    Node newTallNode = insertNode(nodeFound, value);
                    newTallNode.setBelow(newTargetNode);
                    nodeFound.getAbove().setNext(newTallNode);
                }

            }
         }
        return newTargetNode;
    }

    // return true if the SkipList contains the search value otherwise return false.
    @Override
    public boolean contains(long value) {
        Node node = skipSearch(value);
        boolean valueFound = node.getValue()==value;
        return valueFound;
    }

    public boolean delete(long value) {
        Node nodeToBeDeleted = skipSearch(value);
        Node nextNode = null;
        boolean valueFound = nodeToBeDeleted.getValue()==value;
        if (valueFound)
        {
            //make previous node bypass no to be deleted
            Node previousNode = nodeToBeDeleted.getPrevious();
            if (previousNode!=null){
                if (previousNode==head){
                    previousNode.setBelow(nodeToBeDeleted.getNext());

                }else{
                    previousNode.setNext(nodeToBeDeleted.getNext());
                }
            }

            //make next node bypass no to be deleted
            nextNode =nodeToBeDeleted.getNext();
            if (nextNode!=null){
                nextNode.setPrevious(previousNode);
            }

            // now recursively delete tall nodes for this value
            Node tallNode = nodeToBeDeleted.getAbove();
            if (tallNode!=null){
                tallNode.setBelow(null);
                delete(value);
            }
        }

        nodeCount--;
        if (nodeCount ==0) {
            start = null;
        }else{
            start = head.getBelow();
        }
        return valueFound;
    }
    // locate the node closest to the value
    // having a value less than or equal to the searched target value
    // Search next and below successive nodes
    // this will return the node with the search value if one exists
    // otherwise will return the node with the closest value below the search value.
    private Node skipSearch(long value) {

        Node node= head;
        Node nextNode;

        if(head!=null && start!=null && start.getValue()>value)
        {
            node = head;

        } else{

            do {
                //search across
                while (((node.getNext()) != null) && node.getNext().getValue() <= value) {
                    node = node.getNext();
                }
                //search down
                nextNode = node.getBelow();
                if (nextNode != null) {
                    node = nextNode;
                }
            } while (nextNode != null);
        }
        valueNotFound = ((node!=null && node.getValue()!=value)||node==null);

        lastNodeFound = node;
        return node;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public boolean isEmpty() {
        return (nodeCount==0);
    }


    public void print() {
        Node node = start;

        System.out.println("SkipList:");
        if (node==null){
            System.out.println("List is empty");
        }
        while (node!= null) {

            String outputString = node.getValue()+", ";
            System.out.println(outputString);
            node = node.getNext();
        }
    }

}
