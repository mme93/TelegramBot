package telegramm.test;



import telegramm.bot.Bot;
import telegramm.httpRequest.CovidRequest;

public class ChatBotTest {
	/*
	private static Bot testBot;
	private static CovidRequest covidRequest;

	@BeforeAll()
	public static void initializeMockData() {
		covidRequest = new CovidRequest();

		testBot = new Bot();
	}

	@Test
	@DisplayName("Check if you are in the right case")
	public void testSwitchToCommand() {
		assertTrue(testBot.switchRestToCommand("/infection", covidRequest).contains("Neuinfektionen"));
		assertTrue(testBot.switchRestToCommand("/infected", covidRequest).contains("Gesamtcoronainfektionen"));
		assertTrue(testBot.switchRestToCommand("/increase", covidRequest).contains("prozentuale Anstieg"));
		assertTrue(testBot.switchRestToCommand("/average", covidRequest).contains("Anstieg"));
		assertTrue(testBot.switchRestToCommand("/incidencevalue", covidRequest).contains("Inzidenzwert"));
		assertTrue(
				testBot.switchRestToCommand("/incidencegoal", covidRequest).contains("Ziel-Inzidenzwert is"));
		assertTrue(testBot.switchRestToCommand("/days", covidRequest).contains("dauert aktuell"));

		assertTrue(testBot.switchRestToCommand("default", covidRequest)
				.contains("Tut mir leid, diesen Befehl verstehe ich nicht."));
	}

	@Test
	@DisplayName("Check the Botname")
	public void testGetBotName() {
		assertEquals(testBot.getBotUsername(), "CoronaDailyUpdateBot");
	}

	@Test
	@DisplayName("Check the BotToken")
	public void testGetBotToken() {
		assertEquals(testBot.getBotToken(), "1645650570:AAGIOWNjEgsV_I1c3RNjJTcsliXo5eKQj3E");
	}
	*/
}
