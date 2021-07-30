/*
 * Copyright (c) 2021, Cyborger1
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ectofuntusinfo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import net.runelite.client.util.QuantityFormatter;

public class EctofuntusInfobox extends InfoBox
{
	private final EctofuntusInfoPlugin plugin;

	public EctofuntusInfobox(BufferedImage image, EctofuntusInfoPlugin plugin)
	{
		super(image, plugin);
		this.plugin = plugin;
	}

	@Override
	public String getText()
	{
		return QuantityFormatter.formatNumber(plugin.getStoredTokens());
	}

	@Override
	public Color getTextColor()
	{
		int tokens = plugin.getStoredTokens();
		if (tokens >= EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT)
		{
			return Color.RED;
		}
		else if (tokens >= EctofuntusInfoPlugin.WARN_TOKEN_AMOUNT)
		{
			return Color.YELLOW;
		}

		return null;
	}

	@Override
	public String getTooltip()
	{
		int tokens = plugin.getStoredTokens();
		StringBuilder sb = new StringBuilder();
		sb.append("You have ")
			.append(QuantityFormatter.formatNumber(tokens))
			.append(" Ecto-tokens to collect.");

		if (tokens >= EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT)
		{
			sb.append("</br>You must collect your Ecto-tokens before you can continue using the Ectofuntus.");
		}
		else
		{
			sb.append("</br>You can still create ")
				.append(QuantityFormatter.formatNumber(EctofuntusInfoPlugin.MAX_TOKEN_AMOUNT - tokens))
				.append(" more Ecto-tokens.");
		}

		return sb.toString();
	}

	@Override
	public boolean render()
	{
		return plugin.isInEctofuntusRegion() && plugin.getStoredTokens() > 0;
	}
}
