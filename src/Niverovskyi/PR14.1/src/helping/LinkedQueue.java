package helping;

import java.util.Iterator;

public class LinkedQueue<Item> implements Iterable<Item>{
	private Node first,last;
	private int count =0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void enqueue(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		count++;
		if (isEmpty())
			first = last;
		else
			oldLast.next=last;
		
	}

	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		count--;
		if (isEmpty()) last =null;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}
	private class QueueIterator implements Iterator<Item>{
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
	
		}
		
	}
}
