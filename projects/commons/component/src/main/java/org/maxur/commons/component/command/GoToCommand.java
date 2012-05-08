package org.maxur.commons.component.command;

import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * <p>GoToCommand class.</p>
 *
 * @author Maxim Yunusov
 * @version 1.0 15.04.12
 */
public final class GoToCommand extends Command {

    private static final long serialVersionUID = 9012489431437576131L;

    private Class<? extends IRequestablePage> targetPage;

    /**
     * <p>Private Constructor for Command.</p>
     *
     * This Command must be constructed with GoToCommand.Builder only.
     *
     */
    private GoToCommand() {
    }

    /**
     * Returns a instance of GoToCommand builder.
     *
     * @return a instance of GoToCommand builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * {@inheritDoc}
     * @see Command#execute()
     */
    @Override
    public void execute() {
        RequestCycle.get().setResponsePage(targetPage);
    }

    /**
     * {@inheritDoc}
     * @see Command#isActive()
     */
    @Override
    public boolean isActive() {
        return getComponent() != null && this.targetPage.equals(getComponent().getPage().getClass());
    }

    /**
     * The GoToCommand instance builder.
     *
     * see GoF pattern 'Builder'.
     */
    public static final class Builder {

        private final GoToCommand result = new GoToCommand();

        /**
         * <p>Private Constructor for Builder.</p>
         *
         * This Builder must be constructed with GoToCommand.builder() static method only.
         *
         */
        private Builder() {
        }

        /**
         * Sets Wicket Page as target to GoToCommand.
         *
         * @param targetPage This is a Wicket Page as target to GoToCommand.
         * @return This instance of  Builder. (see DSL pattern 'Expression Builder')
         *
         */
        public Builder setAsTarget(final Class<? extends IRequestablePage> targetPage) {
            result.targetPage = targetPage;
            return this;
        }

        /**
         * Sets a key of property with title message.
         *
         * @param key This is a key of property with title message.
         * @return This instance of  Builder. (see DSL pattern 'Expression Builder')
         */
        public Builder withTitleKey(final String key) {
            result.setTitleKey(key);
            return this;
        }

        /**
         * Builds a GoToCommand instance with target page and title key.
         *
         * @return The GoToCommand instance.
         */
        public GoToCommand build() {
            if (result.getTitleKey() == null) {
                throw new AssertionError("The title key is not defined for this GoToCommand");
            }
            if (result.targetPage == null) {
                throw new AssertionError("The target page is not defined for this GoToCommand");
            }
            return result;
        }

    }

}
