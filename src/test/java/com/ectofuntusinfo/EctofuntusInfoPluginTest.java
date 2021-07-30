package com.ectofuntusinfo;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class EctofuntusInfoPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(EctofuntusInfoPlugin.class);
		RuneLite.main(args);
	}
}