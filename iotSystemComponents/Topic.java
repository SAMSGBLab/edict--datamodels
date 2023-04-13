package iotSystemComponents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class Topic {

	@JsonProperty("id")
	public String topicId;
	public String topicName;
	public int topicPriority = 0;
	public int jmtPriority = 0;
	public ArrayList<String> publishers;
	public ArrayList<String> subscribers;
	public ArrayList<String> subTopics = new ArrayList<String>();

	public Topic() {}
	public Topic(String topicId, String topicName, ArrayList<String> publishers, ArrayList<String> subscribers) {
		this.topicId = topicId;
		this.topicName = topicName;
		this.publishers = (ArrayList<String>) publishers.clone();
		this.subscribers = (ArrayList<String>) subscribers.clone();
	}

	@JsonProperty("name")
	private void unpackName(Map<String, Object> nameMap) {
		if (nameMap.containsKey("value")) {
			topicName = (String) nameMap.get("value");
		}
	}
	@JsonProperty("priority")
	private void unpackPriority(Map<String, Object> priorityMap) {
		if (priorityMap.containsKey("value")) {
			topicPriority = (int) priorityMap.get("value");
		}
	}
	@JsonProperty("isCapturedBy")
	private void unpackPublishers(Map<String, Object> publishersMap) {
		if (publishersMap.containsKey("object")){
			publishers = (ArrayList<String>) publishersMap.get("object");
		}
	}
	@JsonProperty("isReceivedBy")
	private void unpackSubscribers(Map<String, Object> subscribersMap) {
		if (subscribersMap.containsKey("object")){
			subscribers = (ArrayList<String>) subscribersMap.get("object");
		}
	}
}
