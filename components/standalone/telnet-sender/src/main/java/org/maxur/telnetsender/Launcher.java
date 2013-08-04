/*
 * Copyright (c) 2013 Maxim Yunusov
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.maxur.telnetsender;

/**
 * Telnet Sender Launcher.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>31/07/13</pre>
 */
public final class Launcher {

    private static final int REQ_ARGS_NUMBER = 3;

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Launcher() {
    }

    /**
     * Command line entry point. This method kicks off the building of a project object
     * and executes a build using either a given target or the default target.
     *
     * @param args - host port command.
     */
    public static void main(String[] args) {
        try {
            makeSender(args).send();
        } catch (InterruptedException e) {
            Terminal.error("Unexpected exception.\n" + "Usage with arguments: <host> <port> <command>");
        } catch (IllegalArgumentException e) {
            Terminal.error("Illegal argument exception.\n" + "Usage with arguments: <host> <port> <command>");
        }
    }

    private static Sender makeSender(final String[] args) {
        if (args.length != REQ_ARGS_NUMBER) {
            throw new IllegalArgumentException();
        }
        try {
            final String host = args[0];
            final int port = Integer.parseInt(args[1]);
            final String command = args[2];
            return new Sender(host, port, command);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }

    }

}
