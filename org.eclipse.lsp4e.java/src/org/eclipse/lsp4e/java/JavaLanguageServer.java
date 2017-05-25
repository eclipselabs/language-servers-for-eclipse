/*******************************************************************************
 * Copyright (c) 2017 Rogue Wave Software Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Michał Niewrzał (Rogue Wave Software Inc.) - initial implementation
 *******************************************************************************/
package org.eclipse.lsp4e.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.lsp4e.LanguageServerPlugin;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;
import org.osgi.framework.Bundle;

public class JavaLanguageServer extends ProcessStreamConnectionProvider {

	public JavaLanguageServer() {
		List<String> commands = new ArrayList<>();
		commands.add("java");
		commands.add("-Declipse.application=org.eclipse.jdt.ls.core.id1");
		commands.add("-Dosgi.bundles.defaultStartLevel=4");
		commands.add("-Declipse.product=org.eclipse.jdt.ls.core.product");
		commands.add("-Dlog.protocol=true");
		commands.add("-Dlog.level=ALL");
		commands.add("-noverify");
		commands.add("-Xmx1G");
		commands.add("-jar");
		commands.add("./plugins/org.eclipse.equinox.launcher_1.4.0.v20161219-1356.jar");
		commands.add("-configuration");
		if (Platform.getOS().equals(Platform.OS_WIN32)) {
			commands.add("./config_win");
		}
		if (Platform.getOS().equals(Platform.OS_LINUX)) {
			commands.add("./config_linux");
		}
		if (Platform.getOS().equals(Platform.OS_MACOSX)) {
			commands.add("./config_mac");
		}
		commands.add("-data");
		commands.add("./data");

		setCommands(commands);

		Bundle bundle = Activator.getContext().getBundle();
		Path workingDir = Path.EMPTY;
		try {
			workingDir = new Path(FileLocator.toFileURL(FileLocator.find(bundle, new Path("server"), null)).getPath());
			setWorkingDirectory(workingDir.toOSString());
		} catch (IOException e) {
			LanguageServerPlugin.logError(e);
		}
	}

	@Override
	public String toString() {
		return "Java Language Server" + super.toString();
	}

}
