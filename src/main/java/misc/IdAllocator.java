package misc;

import java.util.*;

public class IdAllocator {

	static class Range implements Comparable<Range>{
		int start, end;

		Range(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public int compareTo(Range o) {
			// todo
			return 0;
		}
	}

	TreeMap<Range, Boolean> ids = new TreeMap<>();

	int allocId() {

		Map.Entry<Range, Boolean> entry = ids.firstEntry();// return the item
															// before the gap
		if (entry != null) {
			int id = entry.getKey().end + 1;
			entry.getKey().end = id;
			// merge entry with next entry if necessary
			Map.Entry<Range, Boolean> nextentry = ids.ceilingEntry(entry.getKey());
			if (nextentry.getKey().start == entry.getKey().end + 1) {
				entry.getKey().end = nextentry.getKey().end;
				ids.remove(nextentry.getKey());
			}
			return id;
		} else {
			entry = ids.lastEntry();
			if (entry == null) {
				// recordAllocation(id);
				ids.put(new Range(0, 0), true);
				return 0;
			} else { // last
				int id = entry.getKey().end + 1;
				// equivalent to record allocation
				entry.getKey().end = id;
				return id;
			}
		}

	}

	void releaseId(int id) throws Exception {

		Map.Entry<Range, Boolean> entry = ids.floorEntry(new Range(id, id));
		if (inRange(entry.getKey(), id)) {
			if (unitSizedRange(entry.getKey())) {
				ids.remove(entry.getKey());
			} else {
				Range k = entry.getKey();
				if (k.start == id) {
					k.start = id + 1;
				} else if (k.end == id) {
					k.end = id - 1;
				} else {
					// split
					int oldEnd = k.end;
					k.end = id - 1;
					ids.put(new Range(id + 1, oldEnd), true);
				}
			}
		} else {
			throw new Exception("not allocated id");
		}

	}

	boolean inRange(Range range, int num) {
		// todo
		return true;
	}

	boolean unitSizedRange(Range range) {
		// todo
		return true;
	}

}
