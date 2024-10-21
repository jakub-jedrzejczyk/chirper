package win.those.aegiru.chirper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class ChirperApplication {

	public static void main(String[] args) {
		//SpringApplication.run(ChirperApplication.class, args);

		// 2.1. Create some users and posts.

		List<User> users = new ArrayList<User>();

		User ElonMusk = User.builder()
				.username("elonmusk")
				.password("password")
				.displayName("Elon Musk")
				.build();

		User DonaldTrump = User.builder()
				.username("donaldtrump")
				.password("password")
				.displayName("Donald Trump")
				.build();

		User JoeBiden = User.builder()
				.username("joebiden")
				.password("password")
				.displayName("Joe Biden")
				.build();

		users.add(ElonMusk);
		users.add(DonaldTrump);
		users.add(JoeBiden);

		Post post1 = Post.builder().content("I am the president of the United States.").author(DonaldTrump).build();
		Post post2 = Post.builder().content("I am the president of the United States.").author(JoeBiden).build();
		Post post3 = Post.builder().content("I am the president of the United States.").author(ElonMusk).build();

		Post post4 = Post.builder().content("I am very rich.").author(DonaldTrump).build();
		Post post5 = Post.builder().content("I am very rich.").author(ElonMusk).build();

		Post post6 = Post.builder().content("I will win the presidential election.").author(DonaldTrump).build();
		Post post7 = Post.builder().content("I will not partake in the presidential election.").author(JoeBiden).build();

		Post post8 = Post.builder().content("I am the CEO of Tesla.").author(ElonMusk).build();
		Post post9 = Post.builder().content("I am the CEO of SpaceX.").author(ElonMusk).build();

		ElonMusk.getPosts().add(post3);
		ElonMusk.getPosts().add(post5);
		ElonMusk.getPosts().add(post8);
		ElonMusk.getPosts().add(post9);

		DonaldTrump.getPosts().add(post1);
		DonaldTrump.getPosts().add(post4);
		DonaldTrump.getPosts().add(post6);

		JoeBiden.getPosts().add(post2);
		JoeBiden.getPosts().add(post7);

		// 2.2 Print all users and their posts in original order using nested for each lambda expressions.

		users.forEach(user -> {
			System.out.println(user);
			user.getPosts().forEach(post -> {
				System.out.println("\t" + post);
			});
		});

		// 3. Using single Stream API pipeline create Set collection all posts (from all users). Then using second pipeline print it.

		System.out.println("\n3.\n");

		Set<Post> allPosts = users.stream()
				.flatMap(user -> user.getPosts().stream())
				.collect(Collectors.toSet());

		allPosts.stream().forEach(System.out::println);

		// 4. Using single Stream API pipeline filter posts collection created earlier (by one selected property), then sort it (by one different property) and print it.

		System.out.println("\n4.\n");

		allPosts.stream()
				.filter(post -> post.getContent().contains("president"))
				.sorted((postA, postB) -> postA.getAuthor().getDisplayName().compareTo(postB.getAuthor().getDisplayName()))
				.forEach(System.out::println);

		// 5. Using single Stream API pipeline transform post collection created earlier into stream of DTO objects, then sort them using natural order and collect them into List collection. Then using second pipeline print it.

		System.out.println("\n5.\n");

		List<PostDTO> postDTOs = allPosts.stream()
				.map(post -> PostDTO.builder().id(post.getId()).content(post.getContent()).author(post.getAuthor().getDisplayName()).build())
				.sorted()
				.collect(Collectors.toList());

		postDTOs.stream().forEach(System.out::println);

		// 6. Using serialization mechanism store collection of users in the binary file, then read it from it and print all users with posts.

		System.out.println("\n6.\n");

		try {
			FileOutputStream fileOutput = new FileOutputStream("users.ser");
			var objectOutput = new java.io.ObjectOutputStream(fileOutput);

			objectOutput.writeObject(users);

			objectOutput.close();
			fileOutput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			var objectInput = new java.io.ObjectInputStream(new java.io.FileInputStream("users.ser"));

			List<User> usersFromFile = (List<User>) objectInput.readObject();

			usersFromFile.forEach(user -> {
				System.out.println(user);
				user.getPosts().forEach(post -> {
					System.out.println("\t" + post);
				});
			});

			objectInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 7. Using Stream API parallel pipelines with custom thread pool execute some task on
		//    each user. For example task can be printing each user with
		//    intervals using Thread.sleep() to simulate workload. Observer result with
		//    different custom pool sizes. For thread pool use ForkJoinPool. Remember about
		//    closing the thread pool.

		System.out.println("\n7.\n");

		java.util.concurrent.ForkJoinPool customThreadPool = new java.util.concurrent.ForkJoinPool(1);

		allPosts.stream().parallel().forEach(user -> {
			try {
				System.out.println(user);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	});

		customThreadPool.shutdown();
	}

}
