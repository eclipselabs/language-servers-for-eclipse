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
package org.eclipse.lsp4e.python;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.lsp4e.server.ProcessStreamConnectionProvider;

public class PythonLanguageServer extends ProcessStreamConnectionProvider {

	public PythonLanguageServer() {
		List<String> commands = new ArrayList<>();
		commands.add("pyls");
		setCommands(commands);
		setWorkingDirectory(Platform.getLocation().toOSString());
	}

	@Override
	public String toString() {
		return "Python Language Server: " + super.toString();
	}

}
