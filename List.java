public class List<E> {
    private static class Node<E>{
        private E element; //referenced to the element at this node
        private Node<E> next; //the next node (link)

        public Node(){ }

        public Node(E e) {
            element = e;
        }

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    //declarations
    private Node<E> head = null; //the head of a node
    private Node<E> tail = null; //the tail of a node
    private int size = 0; //the side of the singly linked list
    public List(){} //constructs an empty list

    //Method declarations
    public int size() { //accesses the size of the singly linked list
        return size;
    }

    public boolean isEmpty() { //checks to see if the linkedlist is empty or not
        return size == 0;
    }

    public E first(){
        if( isEmpty() )
            return null;
        return head.getElement();
    }

    public E last(){
        if( isEmpty() )
            return null;
        return tail.getElement();
    }

    //Actual Code pertaining to the MP3 player--------------------------------------------------------------------------

    public void insertItem(E name) {
        Node<E> node = new Node<>(name, null);

        if ( isEmpty() ){
            head = node;
        }
        else{
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    public void insertItem(E name, int index) {
        if(index < 0 || index > size - 1){
            System.out.println("The index is out of bounds");
        }

        else if(index == size - 1){
            insertItem(name);
            size++;
        }

        else if(index == 0){
            head = new Node<>(name, head);
            size++;
        }

        else{
            Node<E> newNode = new Node<>(name);
            Node<E> node = head.getNext();
            Node<E> prevNode = head;

            for(int i = 0; i < index - 1; i++){
                node = node.getNext();
                prevNode = prevNode.getNext();
            }

            prevNode.setNext(newNode);
            newNode.setNext(node);
            size++;
        }
    }

    public boolean removeItem(E name) {
        Node<E> node = head;
        boolean result = false;
        if(head.getElement() == name) {
            removeItem(0);
            result = true;
        }
        else if(tail.getElement() == name) {
            removeItem(size - 1);
            result = true;
        }
        else{
            int index = -1;

            for(int i = 0; i < size - 1; i++){
                if(node.getElement() == name){
                    result = true;
                    index = i;
                    node = node.getNext();
                }
                else
                    node = node.getNext();

            }
            if (result)
                removeItem(index);
        }
        return result;
    }

    public void removeItem(int index) {
        if(index < 0 || index > size - 1){
            System.out.println("The index is out of bounds");
        }
        else if(index == size - 1){
            Node<E> prevNode = head;

            for(int i= 0; i < size - 2; i++)
                prevNode = prevNode.getNext();

            prevNode.setNext(null);
            tail = prevNode;
            size--;
        }
        else if(index == 0){
            head = head.getNext();
            size--;
        }

        else{
            Node<E> node = head.getNext();
            Node<E> prevNode = head;

            for(int i = 0; i < index - 1; i++){
                node = node.getNext();
                prevNode = prevNode.getNext();
            }

            prevNode.setNext(node.getNext());
            node.setNext(null);
            size--;
        }
    }

    boolean contains(String name){
        Node<E> node = head;
        for (int i = 0; i < size - 1; i++) {
            if (name == node.getElement())
                return true;
            else
                node = node.getNext();
        }
        return false;
    }

    public void clear(){
        Node<E> tempNode = head;
        int tempSize = size;

        for (int i = 0; i < tempSize - 1; i++){
            removeItem(0);
        }
        head = null;
        tail = null;
        size = 0;
    }

    public String get(int index){
        Node<E> node = head;

        for(int i = 0; i < index; i++){
            node = node.getNext();
        }

        return (String) node.getElement();
    }

    @Override
    public String toString(){
        String temp = "";
        Node<E> node = head;

        for(int i = 0; i < size; i++){
            temp += node.getElement() + "\n";
            node = node.getNext();
        }
        return temp;
    }

    public String toHTMLString() {
        String temp = "<html>PlayList<br/>";
        Node<E> node = head;

        for(int i = 0; i < size; i++){
            temp += node.getElement() + "\n";
            node = node.getNext();
        }

        temp = temp + "</html>";
        return temp;
    }
}
