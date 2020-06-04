import java.util.function.Consumer;

interface RestInterface {
	public String listen(String msg);
}

class C {

	Event currentEvent;
	int eventCounter = 0;
	Event[] events = new Event[100];

    public static void main(String args[]) {

		C c = new C();

        c.new_event()
			.path("hello")
			.name("Leandro")
			.age(33);

		c.answerEvent("hello", "nice Thursday");
    }

	void answerEvent(String eventPath, String msg) {
		for (int i = 0; i < eventCounter; ++i) {
			if (events[i].path.equals(eventPath)) {
				events[i].fire(msg, s -> "Niceee: " + s, events[i]::nameAge);
				break;
			}
		}
	}

	C new_event() {
		currentEvent = new Event();
		events[eventCounter] = currentEvent;
		++eventCounter;
		return this;
	}

	C age(int s) {
		currentEvent.age = s;
		return this;
	}

	C name(String s) {
		currentEvent.name = s;
		return this;
	}

	C path(String s) {
		currentEvent.path = s;
		return this;
	}

	static class Event {
		int age;
		String name;
		String path;

		public void nameAge(String msg) {
			System.out.println("I got this message: " + msg);
			System.out.println(name + " is " + age + " years old!!!");
		}

		public String fire(String msg, RestInterface ri, Consumer<String> c) {
			c.accept(ri.listen(msg));

			return name;
		}
	}
}
