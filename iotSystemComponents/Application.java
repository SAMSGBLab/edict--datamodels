package iotSystemComponents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Map;

public class Application implements Subscriber{

	@JsonProperty("id")
	public String applicationId;

	public String applicationName;
	public String applicationCategory;
	public int processingRate;
	public String processingDistribution;
	public ArrayList<String> subscribedTopics;
	public int priority;		
	public int jmtPriority;			

	public Application(){

	}
	public Application (String applicationId, String applicationName, String applicationCategory, int processingRate, 
			String processingDistribution, ArrayList<String>subscribedTopics, int priority) {
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationCategory = applicationCategory;
		this.processingRate = processingRate;
		this.processingDistribution = processingDistribution;
		this.subscribedTopics = (ArrayList<String>) subscribedTopics.clone();
		this.priority = priority;
		this.jmtPriority = 0;
	}

	@JsonProperty("name")
	private void unpackName(Map<String, Object> nameMap) {
		if (nameMap.containsKey("value")) {
			applicationName = (String) nameMap.get("value");
		}
	}

	@JsonProperty("priority")
	private void unpackPriority(Map<String, Object> priorityMap) {
		if (priorityMap.containsKey("value")) {
			priority = (int) priorityMap.get("value");
		}
	}

	@JsonProperty("processingRate")
	private void unpackProcessingRate(Map<String, Object> processingRateMap) {
		if (processingRateMap.containsKey("value")) {
			processingRate = (int) processingRateMap.get("value");
		}
	}
	@JsonProperty("processingDistribution")
	private void unpackProcessingDistribution(Map<String, Object> processingDistributionMap) {
		if (processingDistributionMap.containsKey("value")) {
			processingDistribution = (String) processingDistributionMap.get("value");
		}
	}
	@JsonProperty("applicationCategory")
	private void unpackApplicationCategory(Map<String, Object> categoryMap) {
		if (categoryMap.containsKey("value")) {
			applicationCategory = (String) categoryMap.get("value");
		}
	}
	@JsonProperty("receivesObservation")
	private void unpackSubscribedTopics(Map<String, Object> receivesObservationMap) {
		if (receivesObservationMap.containsKey("object")) {
			Object object = receivesObservationMap.get("object");
			if (object instanceof ArrayList) {
				subscribedTopics = (ArrayList<String>) object;
			}
		}
	}
}
