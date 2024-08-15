package pe.gob.msi.presentation.base.mvp

interface ActivityView {
    /**
     * Inicia la vista del tool bar
     */
    fun initToolbar()
    /**
     * Inicia los componentes relaciados a la vista
     */
    fun initComponent()
    /**
     * Inicia los eventos clicks
     */
    fun initClickListener()
}