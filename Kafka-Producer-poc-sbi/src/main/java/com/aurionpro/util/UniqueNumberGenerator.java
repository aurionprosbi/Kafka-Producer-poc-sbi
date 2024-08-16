package com.aurionpro.util;

import java.time.Instant;

public class UniqueNumberGenerator {

	private static long lastTimestamp = 0;
	private static int sequence = 0;

	public synchronized static long generateUniqueNumber() {
		long currentTimestamp = Instant.now().toEpochMilli();

		if (currentTimestamp == lastTimestamp) {
			sequence++;
		} else {
			sequence = 0;
			lastTimestamp = currentTimestamp; // Update the last recorded timestamp
		}

		long uniqueNumber = (currentTimestamp << 10) | (sequence & 0x3FF);

		return uniqueNumber;
	}

	 
}
