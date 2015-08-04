package com.github.miyasum.sandbox.args4j;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

/**
 * args4j でサブコマンドを使う
 */
public class SubCommandSample {

	/**
	 * 引数によって実行するオブジェクトを切り替える
	 */
	@Argument(handler = SubCommandHandler.class)
	@SubCommands({
		@SubCommand(name = "hello", impl = HelloCommand.class),
		@SubCommand(name = "goodbye", impl = GoodbyeCommand.class)
	})
	private Command command;

	public static void main(String[] args) throws CmdLineException {
		SubCommandSample subcommand = new SubCommandSample();
		new CmdLineParser(subcommand).parseArgument(args);
		subcommand.command.execute();
	}

	/**
	 * コマンド
	 */
	public static interface Command {
		public void execute();
	}

	/**
	 * こんにちは
	 */
	public static class HelloCommand implements Command {
		@Override
		public void execute() {
			System.out.println("Hello");
		}
	}

	/**
	 * さようなら
	 */
	public static class GoodbyeCommand implements Command {
		@Override
		public void execute() {
			System.out.println("Goodbye");
		}
	}
}
