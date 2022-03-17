package testGet;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import toto.xdev.modet.Message;

public class GetModel {

	public static void main(String[] args) {

//		try {
//			HttpResponse<String> resp =  client.send(req, BodyHandlers.ofString());
//			System.out.println(resp.statusCode());
//			System.out.println(resp.body());
//		} catch (Exception e) {
//			// TODO: handle exception
//		}

		try {
			for (;;) {
				for (int i = 1; i <= 50; i++) {
					Message message = new Message();
					message.setName("toot_1");
					message.setHumidity(Long.valueOf(i));
					message.setTemperature(Long.valueOf(i));

					String url = "http://localhost:8080/post?id=3&name=" + message.getName() + "&temperature="
							+ message.getTemperature() + "&humidity=" + message.getHumidity();

					//System.out.print(url);

						HttpRequest req = HttpRequest.newBuilder()
								.uri(URI.create(url))
								.GET()
								.version(Version.HTTP_2)
								.build();
						
						HttpClient client = HttpClient.newBuilder().build();
						
						HttpResponse<String> resp =  client.send(req, BodyHandlers.ofString());
						System.out.println(resp.statusCode());
						System.out.println(resp.body());
					Thread.sleep(10000);
					//System.out.println();// new line
				}

//				int term = 6;
//				for (int i = 1; i <= term; i++) {
//					
//					for (int j = term; j >= i; j--) {
//						System.out.print("* " + j);
//						
//						Thread.sleep(1000);
//						
//					}
//					System.out.println();// new line
//				}
			}
		} catch (Exception e) {
			System.out.println("Terapong potisuwan");
		}
	}

}
