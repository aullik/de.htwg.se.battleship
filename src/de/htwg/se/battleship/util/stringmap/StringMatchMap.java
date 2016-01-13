package de.htwg.se.battleship.util.stringmap;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * StringMatchMap is a Map with a string as key. Will return closest matching strings
 * Created by niwehrle on 25.11.2015.
 */
public class StringMatchMap<V> {

   private static char[] EMPTY = new char[0];
   private static char[] INIT = new char[] {' '};


   private Node head;
   private int size;

   public StringMatchMap() {
      clear();
   }

   /**
    * Clears this map
    */
   public void clear() {
      head = new Node(null, INIT);
      head.seq = EMPTY;
      size = 0;
   }


   protected static class Node {

      private static int A_TO_INT = (int) 'a';
      private static int NUM_CHARS = 26;
      private Object elem;
      private Node[] childs;
      private List<Node> childList;
      private char[] seq;
      private Node parent;

      protected Node(Node parent, char[] seq) {
         this(null, parent, seq);
      }

      protected Node(Object elem, Node parent, char[] seq) {
         this(elem, parent, seq, new Node[NUM_CHARS], new LinkedList<>());
      }

      protected Node(final Object elem, Node parent, char[] seq, Node[] childs, List<Node> childList) {
         if (seq == null || seq.length == 0) {
            throw new IllegalArgumentException("no KeyString supplied");
         }
         this.elem = elem;
         this.parent = parent;
         this.seq = seq;
         this.childs = childs;
         this.childList = childList;
      }


      private Node lookup(CharArrayIter iter) {
         int startPos = iter.getPos();
         Node n;
         n = _lookup(iter);
         //reset iter to start of node if this is the end node
         if (n == this)
            iter.setTo(startPos);
         else
            n = n.lookup(iter);
         return n;
      }

      private Node _lookup(CharArrayIter iter) {

         if (!compareSeq(iter)) {
            return this;
         }

         //found this;
         if (!iter.hasNext())
            return this;

         Node child = getChildNode(iter.next());
         if (child == null)
            return this;
         else
            return child;
      }


      protected List<Node> getAllChildren() {
         return childList;
      }

      protected boolean remove() {
         if (parent == null)
            return false;

         elem = null;

         if (childList.size() == 0) {
            parent.childList.remove(this);
            parent.childs[childPos(seq[0])] = null;
         } else
            checkMergeWithChild();

         parent.checkMergeWithChild();

         return true;
      }

      private void checkMergeWithChild() {
         if (childList.size() != 1)
            return;

         if (parent == null)
            return;

         final Node child = childList.get(0);

         if (elem == null) {
            mergeWithChild(child, child.elem);
         } else if (child.elem == null) {
            mergeWithChild(child, elem);
         } else {
            return;
         }

      }

      private void mergeWithChild(final Node child, Object elem) {
         child.elem = elem;
         child.parent = parent;
         parent.childList.remove(this);
         parent.childList.add(child);
         parent.setChildNode(seq[0], child);
         child.seq = mergeArray(seq, child.seq);

      }

      private char[] mergeArray(char[] a, char[] b) {
         int aLen = a.length;
         int bLen = b.length;
         char[] ret = Arrays.copyOf(a, aLen + bLen);

         System.arraycopy(b, 0, ret, aLen, bLen);

         return ret;
      }

      protected Node addChild(CharArrayIter iter) {
         int pos = iter.getPos();
         if (!compareSeq(iter)) {
            pos = iter.getPos() - pos;
            if (!iter.hasNext() && seq[pos] == iter.get()) { // iter.hasNext() is false and seq not finished
               split(pos + 1);
               return this;
            }
            split(pos);
         } else if (!iter.hasNext()) //compare successful no more chars, HIT
            return this;
         else
            iter.next(); //compare successful jump to next char#

         Node c = new Node(null, this, iter.getRest());

         this.childList.add(c);
         setChildNode(c.seq[0], c);
         return c;
      }


      private void split(int pos) {
         Node c = new Node(this.elem, this, Arrays.copyOfRange(seq, pos, seq.length), this.childs, this.childList);

         this.seq = Arrays.copyOf(seq, pos);
         this.elem = null;
         this.childs = new Node[26];
         this.childList = new LinkedList<>();

         this.childList.add(c);
         setChildNode(c.seq[0], c);
      }

      private Node getChildNode(final char c) {
         return this.childs[childPos(c)];
      }

      private void setChildNode(final char c, final Node node) {
         childs[childPos(c)] = node;
      }


      private boolean compareSeq(final CharArrayIter iter) {

         for (int i = 1; i < seq.length; i++) {
            char c = seq[i];
            if (!iter.hasNext() || c != iter.next()) {
               return false;
            }
         }
         return true;
      }

      private int childPos(final char c) {
         return ((int) c) - A_TO_INT;
      }

      protected void setElem(Object elem) {
         this.elem = elem;
      }

      @SuppressWarnings ("unchecked")
      public <E> E getElem() {
         return (E) this.elem;
      }

   }

   /**
    * @param key key as String of latin characters
    * @return a value associated with this key
    */
   public V get(String key) {
      final CharArrayIter iter = new CharArrayIter(stringToChars(key));
      final Node node = head.lookup(iter);
      if (node.compareSeq(iter) && !iter.hasNext())
         return node.getElem();
      else
         return null;
   }

   /**
    * @param key key as String of latin characters
    * @return the Values which keys are closest to the keystring
    */
   public List<V> getApprox(String key) {
      final CharArrayIter iter = new CharArrayIter(stringToChars(key));
      List<V> ret = new LinkedList<>();
      final Node node = head.lookup(iter);
      addElemsToListRek(ret, node);
      return ret;
   }

   private void addElemsToListRek(List<V> list, Node node) {
      V elem = node.getElem();
      if (elem != null)
         list.add(elem);
      node.getAllChildren().forEach(n -> addElemsToListRek(list, n));
   }

   /**
    * puts this key/value pair into this map
    *
    * @param key   key as String of latin characters
    * @param value value associated with this key
    * @return old value associated with this key or null if non existed
    */
   public V put(String key, V value) {
      final CharArrayIter iter = new CharArrayIter(stringToChars(key));
      Node node = head.lookup(iter);
      node = node.addChild(iter);
      V ret = node.getElem();
      node.elem = value;

      if (ret == null)
         size++;

      return ret;
   }

   /**
    * remove the key/value pair from this map
    *
    * @param key key as String of latin characters
    * @return whether the key/value pair associated with this key has been removed or not
    */
   public boolean remove(String key) {
      boolean ret = _remove(key);
      if (ret)
         size--;
      return ret;
   }

   private boolean _remove(String s) {
      final CharArrayIter iter = new CharArrayIter(stringToChars(s));
      final Node node = head.lookup(iter);
      if (node.compareSeq(iter) && !iter.hasNext()) {
         return node.remove();

      }
      return false;
   }

   /**
    * @param key key as String of latin characters
    * @return whether this map contains the key or not
    */
   public boolean contains(String key) {
      final CharArrayIter iter = new CharArrayIter(stringToChars(key));
      final Node node = head.lookup(iter);

      return node.compareSeq(iter) && !iter.hasNext();
   }

   /**
    * @return returns the amount of key/value pairs in this map
    */
   public int size() {
      return size;
   }

   private char[] stringToChars(String s) {
      final String lower = s.toLowerCase();
      if (!lower.matches("[a-z]*"))
         throw new IllegalArgumentException("Inputstring must only contain Latin characters");
      return lower.toCharArray();
   }

}
