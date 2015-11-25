package de.htwg.se.battleship.util.stringmap;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by niwehrle on 25.11.2015.
 */
public class StringMatchMap<T> {

   private Node head = new Node(null, new char[0]);

/*
   Jenkins
   http://lenny2.in.fh-konstanz.de:8080
   Sonar
   http://lenny2.in.fh-konstanz.de:9000
*/
   private static class CharArrayIter {

      private final char[] array;

      private final int max;
      private int pos;
      private CharArrayIter(final char[] array) {
         this.array = array;
         this.pos = -1;
         this.max = array.length -1;
      }

      public boolean hasNext() {
         return pos < max;
      }

      public char next() {
         pos++;
         return get();
      }

      public char get(){
         return array[pos];
      }

      public void reset(){
         setTo(0);
      }

      public void setTo(final int i){
         pos = i;
      }

      public int getPos(){
         return pos;
      }

      /**
       *
       * @return return rest array starting with current Pos
       */
      public char[] getRest() {
         return Arrays.copyOfRange(array,pos,array.length);
      }
   }


   private static class Node{
      private static int A_TO_INT = (int) 'a';
      private Object elem;
      private Node[] childs;
      private List<Node> childList;
      private char[] seq;
      private Node parent;

      private Node(Node parent, char[] seq) {
         this(null, parent, seq);
      }
      private Node(Object elem, Node parent, char[] seq) {

         this(elem, parent, seq, new Node[26], new LinkedList<>());
      }

      private Node(final Object elem, Node parent, char[] seq, Node[] childs, List<Node> childList) {
         this.elem = elem;
         this.parent = parent;
         this.seq = seq;
         this.childs = childs;
         this.childList = childList;
      }




      private Node lookup(CharArrayIter iter){
         int startPos = iter.getPos();
         Node n;
         n = _lookup(iter);
         //reset iter to start of node if this is the end node
         if(n == this)
            iter.setTo(startPos);
         else
            n = n.lookup(iter);
         return n;
      }

      private Node _lookup(CharArrayIter iter){

         if(!compareSeq(iter)) {
            return this;
         }

         //found this;
         if(!iter.hasNext())
            return this;

         Node child = getChild(iter.next());
         if(child == null)
            return this;
         else
            return child;
      }



      private Node getChild(final char next) {
         return childs[childPos(next)];
      }

      private List<Node> getAllChilds(){
         return childList;
      }

      private void removeChild(Node c){
         if(!childList.remove(c))
            return;

         if(elem == null && childList.size() == 0){
            parent.removeChild(this);
            return;
         }

         if(childList.size() == 1){
            merge(childList.get(0));
            return;
         }

         removeChildNode(seq[0]);
      }

      private void merge(final Node child){
         child.parent = parent;
         child.seq = mergeArray(seq, child.seq);

      }

      private char[] mergeArray(char[] a, char[] b){
         int aLen = a.length;
         int bLen = b.length;
         char[] ret = Arrays.copyOf(a,aLen+bLen);

         System.arraycopy(b, 0, ret, aLen, bLen);

         return ret;
      }


      private void addChild(CharArrayIter iter, Object elem){

         if(!iter.hasNext()){
            this.elem = elem;
            return;
         }

         int pos= iter.getPos();
         boolean hit = false;
         if(!compareSeq(iter)){
            pos = iter.getPos() - pos;
            if(seq[pos] == iter.get()){
               pos++;
               hit = true;
            }
            split(pos);
         }

         //hit
         if(hit) {
            this.elem = elem;
            return;
         }

         Node c;
         c = getChildNode(iter.get());
         if(c == null){
            c = new Node(elem, this, iter.getRest());
            this.childList.add(c);
            addChildNode(c.seq[0], c);
         }else{
            c.addChild(iter, elem);
         }
      }

      private void split(int pos){
         Node c = new Node(this.elem, this, Arrays.copyOfRange(seq,pos,seq.length),this.childs,this.childList);

         this.seq = Arrays.copyOf(seq, pos);
         this.elem = null;
         this.childs = new Node[26];
         this.childList = new LinkedList<>();

         this.childList.add(c);
         addChildNode(c.seq[0], c);
      }

      private Node getChildNode(final char c){
         return this.childs[childPos(c)];
      }

      private void addChildNode(final char c, final Node node) {
         childs[childPos(c)] = node;
      }


      private boolean compareSeq(final CharArrayIter iter){

         for(int i = 1; i < seq.length; i++) {
            char c = seq[i];
            if(!iter.hasNext() || c != iter.next()){
               return false;
            }
         }
         return true;
      }

      private void removeChildNode(final char c) {
         childs[childPos(c)] = null;
      }

      private int childPos(final char c){
         return ((int) c) - A_TO_INT;
      }

   }

   public T get(String s){
      throw new NotImplementedException();
   }

   public String[] getAprox(String s){
      throw new NotImplementedException();
   }

   public void put(String s, T elem){
      throw new NotImplementedException();
   }

   public void remove(String s, T elem){
      throw new NotImplementedException();
   }

   public boolean contains(String s){
      throw new NotImplementedException();
   }

   public int size(){
      throw new NotImplementedException();
   }




}
