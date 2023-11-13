package com.flagcamp.ehub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@SpringBootTest
class EhubApplicationTests {

	@Test
	void contextLoads() {
		String test = "cba";
		int max = 2;
		char[] words = test.toCharArray();
		Stack<Character> stack = new Stack<>();
		stack.push('a');
		for (int i = 0; i < words.length; i++) {
			if (stack.peek() < words[i]) {
				if (max >= words[i] - stack.peek()) {
					max -= words[i] - stack.peek();
					stack.pop();
					stack.push(words[i]);
				}else {
					stack.push((char) (words[i] - max));
					stack.push(words[i]);
					max = 0;
				}
			}
			if (max == 0)
				break;
		}
		List<Character> list = new ArrayList<>();
		while(!stack.isEmpty()) {
			list.add(stack.pop());
		}
		if (list.get(list.size() - 1) != 'a')
		list.add('a');
		if (list.size() < 4) {
			for (int i = 0; i < words.length; i++) {
				if (words[i] <= list.get(0) && words[i] > list.get(1)) {
					words[i] = list.get(1);
				}
			}
		}else {
			for (int i = 0; i < words.length; i++) {
				if (words[i] <= list.get(0) && words[i] > list.get(1)) {
					words[i] = list.get(1);
				} else if (words[i] <= list.get(2) && words[i] > list.get(3)) {
					words[i] = list.get(3);
				}
			}
		}
		System.out.println(String.valueOf(words));
	}

}
